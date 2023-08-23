package com.wants.market.core.mapper;

import com.wants.market.block.dto.BlockList;
import com.wants.market.core.domain.Block;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlockMapper {

    void insertBlock(Block block);

    void removeBlockUser(Long userId, Long blockUserId);

    boolean isUserBlocked(Long userId, Long blockUserId);

    List<BlockList> findBlockUserList(Long userId);

}
