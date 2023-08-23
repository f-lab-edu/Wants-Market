package com.wants.market.like.service

import com.wants.market.core.mapper.HeartMapper
import com.wants.market.heart.dto.CreateHeart
import com.wants.market.heart.service.PostHeartService
import spock.lang.Specification

class PostHeartServiceTest extends Specification {

    HeartMapper heartMapper = Mock()
    PostHeartService postHeartService = new PostHeartService(heartMapper)

    def "게시글에 좋아요를 누르면 게시글 번호와 유저의 번호가 등록 성공"() {
        given:
        CreateHeart createHeart = new CreateHeart(1, 10)
        heartMapper.findHeart(createHeart.getPostId(), createHeart.getUserId()) >> 0

        when:
        postHeartService.createHeart(createHeart)

        then:
        noExceptionThrown()
        1 * heartMapper.insertHeart(_)
    }

    def "유저의 게시글 좋아요 삭제 성공"() {
        given:
        CreateHeart createHeart = new CreateHeart(1, 10)

        when:
        postHeartService.deleteHeart(createHeart)

        then:
        1 * heartMapper.deleteHeart(createHeart.getPostId(), createHeart.getUserId())
    }

    def "유저가 좋아요를 누른 게시글 조회 성공"() {
        given:
        CreateHeart createHeart = new CreateHeart(1, 10)

        when:
        postHeartService.findHeart(createHeart)

        then:
        1 * heartMapper.findHeart(createHeart.getPostId(), createHeart.getUserId())
    }
}
