package com.wants.market.chat.controller;

import com.wants.market.chat.dto.CreateChatRequest;
import com.wants.market.chat.service.ChatService;
import com.wants.market.core.domain.Chat;
import com.wants.market.utils.Responses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/chats")
@RestController
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity createChat(@RequestBody CreateChatRequest createChatRequest) {
        chatService.createChat(createChatRequest);
        return Responses.CREATED;
    }

    @GetMapping("/oldChats")
    public List<Chat> findOldChat(@RequestParam Long chatRoomId, @RequestParam Long chatId) {
        return chatService.oldFindChats(chatRoomId, chatId);
    }

    @GetMapping("/recentChats")
    public List<Chat> findRecentChat(@RequestParam Long chatRoomId, @RequestParam Long chatId) {
        return chatService.findRecentChats(chatRoomId, chatId);
    }
}
