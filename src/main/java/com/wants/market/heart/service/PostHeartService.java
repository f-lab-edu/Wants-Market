package com.wants.market.heart.service;

import com.wants.market.core.domain.Heart;
import com.wants.market.core.mapper.HeartMapper;
import com.wants.market.exception.DuplicatedHeartException;
import com.wants.market.heart.dto.CreateHeart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PostHeartService {

    private final HeartMapper heartMapper;

    @Transactional
    public void createHeart(CreateHeart createHeart) {

        int heartNumber = heartMapper.findHeart(createHeart.getPostId(), createHeart.getUserId());

        if(heartNumber != 0) {
            throw new DuplicatedHeartException("이미 좋아요를 누르셨습니다.");
        }

        Heart heart = Heart.builder()
                .postId(createHeart.getPostId())
                .userId(createHeart.getUserId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        heartMapper.insertHeart(heart);
    }

    public void deleteHeart(CreateHeart createHeart) {
        heartMapper.deleteHeart(createHeart.getPostId(), createHeart.getUserId());
    }

    public int findHeart(CreateHeart createHeart) {
        return heartMapper.findHeart(createHeart.getPostId(),
                createHeart.getUserId());
    }



}
