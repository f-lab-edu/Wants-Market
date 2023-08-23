package com.wants.market.core.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Block {

    private Long id;

    private Long userId;

    private Long blockedUserId;

    private LocalDateTime createdAt;
}
