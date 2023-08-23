package com.wants.market.core.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Chat {

    private Long id;
    private Long userId;
    private Long chatRoomId;

    private String content;
    private LocalDateTime createdAt;

}

