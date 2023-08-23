package com.wants.market.block.service;

import com.wants.market.block.dto.BlockList;
import com.wants.market.block.dto.CreateBlock;
import com.wants.market.chat.service.ChatService;
import com.wants.market.core.domain.Block;
import com.wants.market.core.domain.User;
import com.wants.market.core.mapper.BlockMapper;
import com.wants.market.user.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BlockService {

    private final BlockMapper blockMapper;
    private final SessionService sessionService;
    private final ChatService chatService;

    // 차단 기능
    // 1. 차단하고자 하는 채팅방, 차단하고자 하는 유저아이디를 dto로 보냄
    // 2. 차단된 사용자의 콘텐츠가 차단한 사용자에게 표시되지 않도록 쿼리 로직을 수정
    //    사용자의 피드를 생성할 때 차단 목록을 확인하여 차단된 사용자의 콘텐츠를 제외하고 결과를 반환

    // chat_room_id : 차단된 사용자가 특정 채팅방에서만 차단되도록 설정 가능

    // 차단시
    public void blockUser(CreateBlock createBlock) {
        User user = sessionService.getLoggedInUserFromDatabase();

        if (blockMapper.isUserBlocked(user.getId(), createBlock.getBlockedUserId())) {
            throw new IllegalArgumentException("이미 차단한 사용자입니다.");
        }

        Block block = Block.builder()
                .userId(user.getId())
                .blockedUserId(createBlock.getBlockedUserId())
                .createdAt(LocalDateTime.now())
                .build();

        blockMapper.insertBlock(block);
    }

    // 차단 해제
    public void unblockUser(CreateBlock createBlock) {
        User user = sessionService.getLoggedInUserFromDatabase();
        blockMapper.removeBlockUser(user.getId(), createBlock.getBlockedUserId());
    }

    // 내가 차단한 유저의 목록 조회
    public List<BlockList> blockedList() {
        User user = sessionService.getLoggedInUserFromDatabase();

        return blockMapper.findBlockUserList(user.getId());
    }

}
