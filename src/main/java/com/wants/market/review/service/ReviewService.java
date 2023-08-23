package com.wants.market.review.service;

import com.wants.market.core.domain.Evaluation;
import com.wants.market.core.domain.Post;
import com.wants.market.core.domain.Review;
import com.wants.market.core.domain.User;
import com.wants.market.core.mapper.ReviewMapper;
import com.wants.market.core.mapper.UserMapper;
import com.wants.market.post.service.PostService;
import com.wants.market.review.dto.CreateReview;
import com.wants.market.review.dto.ReviewResponse;
import com.wants.market.user.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;
    private final SessionService sessionService;
    private final UserMapper userMapper;
    private final PostService postService;

    @Transactional
    public void createReview(CreateReview createReview) {
        // post_id > 글쓴사람의 user_id
        User user = sessionService.getLoggedInUserFromDatabase();

        Review review = Review.builder()
                .postId(createReview.getPostId())
                .userId(user.getId())
                .evaluationSet(createReview.getEvaluations())
                .title(createReview.getTitle())
                .content(createReview.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        reviewMapper.insertReview(review);
        updateUserMannerTemp(createReview);
    }

    private void updateUserMannerTemp(CreateReview createReview) {
        Post post = postService.findPostById(createReview.getPostId());
        BigDecimal mannerTemp = calculateMannerTemp(post, createReview.getEvaluations());

        userMapper.updateMannerTemp(mannerTemp, post.getUserId());
    }


    public Review findReviewById(Long id) {
        return reviewMapper.findReviewById(id);
    }

    public ReviewResponse findMyReviews() {
        User user = sessionService.getLoggedInUserFromDatabase();
        return findReviewByUserIdAndConvertType(user.getId());
    }

    public ReviewResponse findReviewByUserId(Long userId) {
        return findReviewByUserIdAndConvertType(userId);
    }

    private ReviewResponse findReviewByUserIdAndConvertType(Long userId) {
        List<Review> reviews = reviewMapper.findReviewsByUserId(userId);
        if (CollectionUtils.isEmpty(reviews)) {
            return new ReviewResponse(new ArrayList<>());
        }

        List<Evaluation> evaluations = new ArrayList<>();
        for (Review review : reviews) {
            evaluations.addAll(review.getEvaluationSet());
        }

        List<ReviewResponse.EvaluationCountPair> evaluationCountPairs = new ArrayList<>();

        for (Evaluation evaluation : Evaluation.values()) {
            long count = evaluations.stream()
                    .filter(ev -> ev == evaluation)
                    .count();
            if (count != 0L) {
                evaluationCountPairs.add(new ReviewResponse.EvaluationCountPair(evaluation, count));
            }
        }

        evaluationCountPairs.sort((evaluationCountPair, t1) -> evaluationCountPair.getCount() > t1.getCount() ? -1 : 1);
        return new ReviewResponse(evaluationCountPairs);
    }

    private BigDecimal calculateMannerTemp(Post post, Set<Evaluation> evaluationSet) {
        Long userId = post.getUserId();
        User user = userMapper.findUserById(userId);

        BigDecimal mannerTemp = user.getMannerTemp();
        mannerTemp = mannerTemp.add(new BigDecimal("0.1"));

        int evaluationSize = evaluationSet.size();
        BigDecimal increaseValue = new BigDecimal("0.1")
                .multiply(new BigDecimal(evaluationSize));

        return mannerTemp.add(increaseValue);
    }
}
