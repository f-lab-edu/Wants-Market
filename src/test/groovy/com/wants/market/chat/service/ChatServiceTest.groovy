package com.wants.market.chat.service

import com.wants.market.chat.dto.CreateChatRequest
import com.wants.market.core.domain.ChatRoom
import com.wants.market.core.domain.User
import com.wants.market.core.mapper.ChatMapper
import com.wants.market.user.service.SessionService
import spock.lang.Specification

class ChatServiceTest extends Specification {
    ChatMapper chatMapper = Mock()
    SessionService sessionService = Mock()
    ChatService chatService = new ChatService(chatMapper, sessionService)


    def "test createChat"() {
        given:
        CreateChatRequest chatRequest = new CreateChatRequest(
                1L, 2L, "testContent"
        )
        User user = new User()
        sessionService.loggedInUserFromDatabase >> user

        when:
        chatService.createChat(chatRequest)

        then:
        1 * chatMapper.insert(_)
    }
}
