package com.wants.market.review.dto;

import com.wants.market.core.domain.Evaluation;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ReviewResponse {
    private final List<EvaluationCountPair> evaluationCountPairs;

    @Data
    @RequiredArgsConstructor
    public static class EvaluationCountPair {
        private final Evaluation evaluation;
        private final Long count;
    }
}
