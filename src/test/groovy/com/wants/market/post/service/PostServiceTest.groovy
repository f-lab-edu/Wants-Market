package com.wants.market.post.service

import com.wants.market.core.domain.Post
import com.wants.market.core.domain.User
import com.wants.market.core.mapper.PostMapper
import com.wants.market.exception.UnAuthorizedAccessException
import com.wants.market.post.dto.CategoryDTO
import com.wants.market.post.dto.PostRequest
import com.wants.market.user.service.SessionService
import spock.lang.Specification

class PostServiceTest extends Specification {

    PostMapper postMapper = Mock()
    CategoryService categoryService = Mock()
    SessionService sessionService = Mock()
    PostService postService = new PostService(postMapper, categoryService, sessionService)

    def "게시글이 성공적으로 등록될 경우 데이터가 정상적으로 저장"() {
        given:
        PostRequest postRequest = new PostRequest("testTitle", "testContent", 1)
        User user = new User()
        sessionService.getLoggedInUserFromDatabase() >> user

        when:
        postService.createPost(postRequest)

        then:
        1 * postMapper.insertPost(_)
    }

    def "게시글 업데이트 성공"() {
        given:
        PostRequest postRequest = new PostRequest("testTitle", "testContent", 1)
        User user = new User()
        sessionService.getLoggedInUserFromDatabase() >> user
        Post post = new Post()
        post.userId = 10L
        user.id = 10L

        when:
        postService.updatePost(postRequest, post)

        then:
        noExceptionThrown()
        1 * postMapper.updatePost(post)
    }

    def "작성자가 일치하지 않을 경우 게시글 업데이트에 실패하고 UnAuthorizedAccessException 발생"() {
        given:
        PostRequest postRequest = new PostRequest("title", "content", 1)
        User user = new User()
        sessionService.getLoggedInUserFromDatabase() >> user
        Post post = new Post()
        user.id = 10L
        post.userId = 5L

        when:
        postService.updatePost(postRequest, post)

        then:
        thrown(UnAuthorizedAccessException)
    }

    def "작성자가 일치할 경우 게시글 삭제에 성공"() {
        given:
        User user = new User()
        sessionService.getLoggedInUserFromDatabase() >> user
        Post post = new Post()
        user.id = 10L
        post.userId = 10L

        when:
        postService.removePost(post)

        then:
        noExceptionThrown()
        1 * postMapper.deletePost(post.getId())
    }

    def "유저가 인증한 주소와 일치하는 글의 목록 조회 성공"() {
        given:
        User user = new User()

        when:
        postService.findPostByAddress(user.getAddress())

        then:
        1 * postMapper.findPostByAddress(user.getAddress())
    }

    def "유저가 인증한 주소와 일치하는 글의 목록과 카테고리별로 조회 성공"() {
        given:
        String address = "석촌동"
        CategoryDTO request = CategoryDTO.builder()
                                .id(2L)
                                .pageSize(5)
                                .pageNo(10)
                                .build()
        int offset = (request.getPageNo() - 1) * request.getPageSize()

        when:
        postService.findPostByAddressAndCategory(address, request)

        then:
        1 * postMapper.findPostByAddressAndCategory(address, request.getId(),
                request.getPageSize(), offset)
    }

}
