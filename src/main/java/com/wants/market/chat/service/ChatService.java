package com.wants.market.chat.service;

import com.wants.market.chat.dto.CreateChatRequest;
import com.wants.market.core.domain.Chat;
import com.wants.market.core.domain.ChatRoom;
import com.wants.market.core.domain.User;
import com.wants.market.core.mapper.BlockMapper;
import com.wants.market.core.mapper.ChatMapper;
import com.wants.market.user.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatMapper chatMapper;
    private final SessionService sessionService;
    private final BlockMapper blockMapper;
    private final ChatRoomService chatRoomService;

    /**
     * 조회 기능
     *
     * 1. 이전 채팅을 계속 불러오는것
     * 2. 폴링
     *      주기적으로 새 채팅을 조회하는 것
     */
    public List<Chat> oldFindChats(Long chatRoomId, Long chatId) {         // 옛날 채팅 조회
        // 데이터를 어떤 기준으로 가져올까 > 둘중에 하나만 먼저 해보기
        // 옛날 채팅 조회 > 클라이언트가 보고 있는 가장 옛날 채팅 아이디를 기준으로 n 개 조회
        // 최신 채팅 조회 > 클라이언트가 보고 있는 가장 마지막 채팅 아이디를 기준으로 최신데이터 조회

        return chatMapper.findOldChats(chatRoomId, chatId);
    }

    public List<Chat> findRecentChats(Long chatRoomId, Long chatId) {     // 최신 채팅 조회
        return chatMapper.findRecentChats(chatRoomId, chatId);
    }

    /**
     * 1. 게스트의 사용자(guest_user_id)는 호스트의 게시글을 보고 채팅하기를 누른다.
     *   -> 사용자는 dto로 게시글의 번호, 채팅 전송할 메세지를 보낸다.
     * 2. 위와 같은 데이터가 DB에 저장된다.
     * 
     * 고려사항
     * 폴링, 웹소켓, FCM, APNS 등 푸시 서비스
     *
     */
    public void createChat(CreateChatRequest createChatRequest) {
        User user = sessionService.getLoggedInUserFromDatabase();

        ChatRoom chatRoom = chatRoomService.findChatRoomById(createChatRequest.getChatRoomId());
        if(blockMapper.isUserBlocked(chatRoom.getHostUserId(), chatRoom.getGuestUserId()) ||
                blockMapper.isUserBlocked(chatRoom.getGuestUserId(), chatRoom.getHostUserId())) {
            throw new IllegalArgumentException("차단한 사용자와 대화를 할 수 없습니다.");
        }

        Chat chat = Chat.builder()
                .userId(user.getId())
                .content(createChatRequest.getContent())
                .chatRoomId(createChatRequest.getChatRoomId())
                .createdAt(LocalDateTime.now())
                .build();


        chatMapper.insert(chat);

        // 푸시 메세지 전송
        // 메세지 대상은 상대방
        // ex ) guest 가 로그인상태 > 메세지전송 시 host 에게 푸시메세지 전송
        // ex ) host 가 로그인상태 > 메세지전송 시 guest 에게 푸시메세지 전송
        sendPushMessage(createChatRequest);
    }

    private void sendPushMessage(CreateChatRequest createChatRequest) {
        // not yet implements
        log.info("sendPushMessage, {}", createChatRequest);
    }

    public List<Chat> findChat() {
        return findChatBySession();
    }

    private List<Chat> findChatBySession() {
        User user = sessionService.getLoggedInUserFromSession();
        return chatMapper.findChat(user.getId());
    }
}

// 로그인한 유저가 차단목록을 조회할 수 있도록 차단목록조회
// 채팅방 만들 때
// * 게시글 못보게