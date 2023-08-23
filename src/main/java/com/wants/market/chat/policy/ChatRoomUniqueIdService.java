package com.wants.market.chat.policy;

import com.wants.market.chat.dto.CreateChatRoomRequest;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomUniqueIdService {
    private static final String CATEGORY_POST = "POST";
    private static final String DELIMITER = ".";

    public String createPostChatRoomId(CreateChatRoomRequest createChatRoomRequest, Long guestUserId) {
        return CATEGORY_POST + DELIMITER + createChatRoomRequest.getPostId()
                + DELIMITER + guestUserId;
    }

    public Long extractPostIdFromUniqueId(String uniqueId) {
        String[] target = uniqueId.split("\\" + DELIMITER);
        
        if (target.length != 3) {
            throw new IllegalArgumentException();
        }
        return Long.valueOf(target[1]);
    }
}

