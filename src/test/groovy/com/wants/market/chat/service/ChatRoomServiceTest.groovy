package com.wants.market.chat.service

import com.wants.market.chat.dto.CreateChatRoomRequest
import com.wants.market.chat.policy.ChatRoomUniqueIdService
import com.wants.market.core.domain.Post
import com.wants.market.core.domain.User
import com.wants.market.core.mapper.ChatRoomMapper
import com.wants.market.post.service.PostService
import com.wants.market.user.service.SessionService
import spock.lang.Specification

class ChatRoomServiceTest extends Specification {

    ChatRoomMapper chatRoomMapper = Mock()
    ChatRoomUniqueIdService chatRoomIdGenerator = Mock()
    SessionService sessionService = Mock()
    PostService postService = Mock()
    ChatRoomService chatRoomService = new ChatRoomService(chatRoomMapper, chatRoomIdGenerator,
            sessionService, postService)

    def "게시글의 유저와 호스트 유저의 채팅방 만들기 성공"() {
        given:
        CreateChatRoomRequest request = new CreateChatRoomRequest(1);
        User user = new User()
        sessionService.getLoggedInUserFromDatabase() >> user
        Post post = new Post()
        post.userId = 2L
        postService.findPostById(_) >> post

        when:
        chatRoomService.createChatRoom(request)

        then:
        1 * chatRoomMapper.insert(_)
    }
}
