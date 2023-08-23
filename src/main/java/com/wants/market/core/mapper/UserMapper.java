package com.wants.market.core.mapper;

import com.wants.market.core.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

@Mapper
public interface UserMapper {

    void insertUser(User user);

    boolean isExistsUserId(String userId);

    User findUserByLoginId(String userId);

    User findUserById(Long id);

    void updateMannerTemp(BigDecimal mannerTemp, Long id);
}
