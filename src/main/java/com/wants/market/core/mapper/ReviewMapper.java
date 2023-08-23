package com.wants.market.core.mapper;

import com.wants.market.core.domain.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    void insertReview(Review review);

    List<Review> findReviewsByUserId(Long userId);

    Review findReviewById(Long id);
}