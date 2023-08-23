package com.wants.market.post.service;

import com.wants.market.core.domain.Review;
import com.wants.market.core.domain.User;
import com.wants.market.core.mapper.PostMapper;
import com.wants.market.core.mapper.ReviewMapper;
import com.wants.market.post.dto.CategoryRequest;
import com.wants.market.review.service.ReviewService;
import com.wants.market.user.service.SessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class ReviewServiceIntegrationTest {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    SessionService sessionService;

    @Test
    void findReviewById() {
        Review review = reviewService.findReviewById(3L);
        System.out.println(review);
    }

//    @Test
//    void findReviewsByUserId() {
//        List<Review> review = reviewService.findMyReviews();
//        System.out.println(review);
//    }
}