package com.wants.market.core.mapper;

import com.wants.market.core.domain.Chat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {

    void insert(Chat chat);

    List<Chat> findChat(Long userId);

    List<Chat> findOldChats(Long chatRoomId, Long chatId);

    List<Chat> findRecentChats(Long chatRoomId, Long chatId);

}
