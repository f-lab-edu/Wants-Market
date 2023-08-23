package com.wants.market.core.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ChatRoom {

    private Long id;
    private String chatRoomUniqueKey;

    private Long hostUserId;
    private Long guestUserId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
