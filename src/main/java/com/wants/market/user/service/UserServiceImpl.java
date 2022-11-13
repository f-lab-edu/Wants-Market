package com.wants.market.user.service;

import com.wants.market.core.domain.User;
import com.wants.market.core.mapper.UserMapper;
import com.wants.market.exception.DuplicatedIdException;
import com.wants.market.user.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signUp(CreateUserRequest createUserRequest) {
        boolean isExistsUserId = userMapper.isExistsUserId(createUserRequest.getUserId());

        if(isExistsUserId) {
            throw new DuplicatedIdException("이미 존재하는 아이디입니다.");
        }

        String hashed = passwordEncoder.encode(createUserRequest.getPassword());
        User user = User.create(createUserRequest, hashed);
        userMapper.insertUser(user);

        return user;
    }

}
