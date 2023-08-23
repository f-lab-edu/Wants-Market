package com.wants.market.core.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Review {

    private Long id;

    private Long postId;

    private Long userId;

    private Set<Evaluation> evaluationSet;

    private String title;

    private String content;

    private LocalDateTime createdAt;




}
