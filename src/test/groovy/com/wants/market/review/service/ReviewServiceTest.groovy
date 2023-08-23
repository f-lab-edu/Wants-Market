package com.wants.market.review.service

import com.wants.market.core.domain.Evaluation
import com.wants.market.core.domain.Review
import com.wants.market.core.domain.User
import com.wants.market.core.mapper.ReviewMapper
import com.wants.market.core.mapper.UserMapper
import com.wants.market.review.dto.CreateReview
import com.wants.market.user.service.SessionService
import spock.lang.Specification

class ReviewServiceTest extends Specification {

    ReviewMapper reviewMapper = Mock()
    SessionService sessionService = Mock()
    UserMapper userMapper = Mock()
    ReviewService reviewService = new ReviewService(reviewMapper, sessionService, userMapper)

    def "test createReview"() {
        given:
        Set<Evaluation> evaluations = new HashSet<>()
        evaluations.add(Evaluation.SET_TIME)
        evaluations.add(Evaluation.FAST_RESPONSE)

        CreateReview createReview = new CreateReview(
                evaluations,
                1L,
                "testTitle",
                "testContent"
        )
        User user = new User()
        user.mannerTemp = new BigDecimal("36.5")
        sessionService.loggedInUserFromDatabase >> user

        when:
        reviewService.createReview(createReview)

        then:
        1 * reviewMapper.insertReview(_)
    }

    def "test findMyReviews - success"() {
        given:
        User user = new User()
        user.id = 10L
        sessionService.loggedInUserFromDatabase >> user

        List<Review> reviews = new ArrayList<>()
        Review review1 = new Review()
        review1.evaluationSet = new HashSet<>()
        review1.evaluationSet.add(Evaluation.FAST_RESPONSE)
        review1.evaluationSet.add(Evaluation.GOOD_MANNER)

        Review review2 = new Review()
        review2.evaluationSet = new HashSet<>()
        review2.evaluationSet.add(Evaluation.FAST_RESPONSE)

        Review review3 = new Review()
        review3.evaluationSet = new HashSet<>()
        review3.evaluationSet.add(Evaluation.FAST_RESPONSE)
        review3.evaluationSet.add(Evaluation.SET_TIME)

        Review review4 = new Review()
        review4.evaluationSet = new HashSet<>()
        review4.evaluationSet.add(Evaluation.FAST_RESPONSE)
        review4.evaluationSet.add(Evaluation.SET_TIME)

        reviews.add(review1)
        reviews.add(review2)
        reviews.add(review3)
        reviews.add(review4)

        reviewMapper.findReviewsByUserId(10L) >> reviews

        when:
        def result = reviewService.findMyReviews()

        then:
        result != null
        println result
        result.evaluationCountPairs.get(0).count == 4
        result.evaluationCountPairs.size() == 3
    }

    def "test findMyReviews - fail"() {
        given:
        User user = new User()
        user.id = 11L
        sessionService.loggedInUserFromDatabase >> user

        List<Review> reviews = new ArrayList<>()
        Review review1 = new Review()
        review1.evaluationSet = new HashSet<>()
        reviews.add(review1)

        reviewMapper.findReviewsByUserId(10L) >> reviews

        when:
        def result = reviewService.findMyReviews()

        then:
        result.evaluationCountPairs.isEmpty()
    }
}
