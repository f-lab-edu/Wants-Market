package com.wants.market.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Heart {

    private Long id;

    private Long postId;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
