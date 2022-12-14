package com.wants.market.core.mapper;

import com.wants.market.core.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insertUser(User user);

    void updateUserProfile(User user);

    void deleteUser(Long id);

    boolean isExistsUserId(String userId);

    User findUserByLoginId(String userId);

    User findUserById(Long id);
}
