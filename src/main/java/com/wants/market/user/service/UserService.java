package com.wants.market.user.service;

import com.wants.market.core.domain.User;
import com.wants.market.user.dto.CreateUserRequest;

public interface UserService {

    public User signUp(CreateUserRequest createUserRequest);

}
