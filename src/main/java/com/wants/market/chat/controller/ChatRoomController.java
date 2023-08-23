package com.wants.market.chat.controller;

import com.wants.market.chat.dto.CreateChatRoomRequest;
import com.wants.market.chat.service.ChatRoomService;
import com.wants.market.core.domain.ChatRoom;
import com.wants.market.utils.Responses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/chatsRooms")
@RestController
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity createChat(@RequestBody CreateChatRoomRequest createChatRoomRequest) {
        chatRoomService.createChatRoom(createChatRoomRequest);
        return Responses.CREATED;
    }

    @GetMapping("/me")
    public List<ChatRoom> findChatRooms() {
        return chatRoomService.findChatRoomsBySession();
    }
}
