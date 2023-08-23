package com.wants.market.review.dto;

import com.wants.market.core.domain.Evaluation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateReview {

    private Set<Evaluation> evaluations;

    private Long postId;

    private String title;

    private String content;
}
