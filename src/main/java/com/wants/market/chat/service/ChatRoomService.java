package com.wants.market.chat.service;

import com.wants.market.chat.dto.CreateChatRoomRequest;
import com.wants.market.chat.policy.ChatRoomUniqueIdService;
import com.wants.market.core.domain.ChatRoom;
import com.wants.market.core.domain.Post;
import com.wants.market.core.domain.User;
import com.wants.market.core.mapper.BlockMapper;
import com.wants.market.core.mapper.ChatRoomMapper;
import com.wants.market.post.service.PostService;
import com.wants.market.user.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomMapper chatRoomMapper;
    private final ChatRoomUniqueIdService chatRoomIdGenerator;
    private final SessionService sessionService;
    private final PostService postService;
    private final BlockMapper blockMapper;

    /**
     * 1. 사용자가 어떤 게시글을 보고 메세지를 전송
     * 1.1 포스트 id
     */
    @Transactional
    public void createChatRoom(CreateChatRoomRequest createChatRoomRequest) {
        User user = sessionService.getLoggedInUserFromDatabase();
        Post post = postService.findPostById(createChatRoomRequest.getPostId());

        if (blockMapper.isUserBlocked(user.getId(), post.getUserId()) ||
                blockMapper.isUserBlocked(post.getUserId(), user.getId())) {
            throw new IllegalArgumentException("차단한 유저와 채팅할 수 없습니다.");
        }

        ChatRoom chatRoom = ChatRoom.builder()
                .chatRoomUniqueKey(
                        chatRoomIdGenerator.createPostChatRoomId(createChatRoomRequest, user.getId()))
                .hostUserId(post.getUserId())
                .guestUserId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();

        chatRoomMapper.insert(chatRoom);
    }

    // 채팅방 목록을 List 타입으로 리턴
    public List<ChatRoom> findChatRoomsBySession() {
        User user = sessionService.getLoggedInUserFromDatabase();
        return findChatRoomsByUserId(user.getId());
    }

    public ChatRoom findChatRoomById(Long chatRoomId) {
        return chatRoomMapper.findChatRoomById(chatRoomId);
    }

    private List<ChatRoom> findChatRoomsByUserId(Long userId) {
        return chatRoomMapper.findChatRoomsByUserId(userId);
    }

}