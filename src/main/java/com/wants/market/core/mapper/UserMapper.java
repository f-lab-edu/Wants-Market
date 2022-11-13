package com.wants.market.core.mapper;

import com.wants.market.core.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insertUser(User user);

    boolean isExistsUserId(String userId);

}
