package com.wants.market.core.mapper;

import com.wants.market.core.domain.Heart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HeartMapper {

    int findHeart(Long postId, Long userId);

    void insertHeart(Heart heart);

    void deleteHeart(Long postId, Long userId);
}
