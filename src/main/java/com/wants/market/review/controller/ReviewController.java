package com.wants.market.review.controller;

import com.wants.market.core.domain.Review;
import com.wants.market.review.dto.CreateReview;
import com.wants.market.review.dto.ReviewResponse;
import com.wants.market.review.service.ReviewService;
import com.wants.market.utils.Responses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Void> createReview(@RequestBody CreateReview createReview) {
        reviewService.createReview(createReview);
        return Responses.CREATED;
    }

    @GetMapping("/myreview")
    public ReviewResponse findMyReviews() {
        return reviewService.findMyReviews();
    }

    @GetMapping("/userReview")
    public ReviewResponse findReviewByUserId(Long userId) {
        return reviewService.findReviewByUserId(userId);
    }


    @GetMapping("/review")
    public Review findReview(@RequestParam Long reviewId) {
        return reviewService.findReviewById(reviewId);
    }
}
