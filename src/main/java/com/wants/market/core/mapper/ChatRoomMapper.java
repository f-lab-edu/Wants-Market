package com.wants.market.core.mapper;

import com.wants.market.core.domain.ChatRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRoomMapper {

    void insert(ChatRoom chatRoom);
    
    List<ChatRoom> findChatRoomsByUserId(Long userId);

    ChatRoom findChatRoomById(Long userId);
}
