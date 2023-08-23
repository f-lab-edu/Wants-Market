package com.wants.market.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateChatRequest {

    private Long chatRoomId;
    private String content;

}

