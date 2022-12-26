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
public class UserRegion {

    private Long id;

    private Long userId;

    private String authStatus;

    private double regionX;

    private double regionY;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
