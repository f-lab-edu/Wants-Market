package com.wants.market.user.service;

import com.wants.market.core.domain.User;
import com.wants.market.user.dto.CreateUserRequest;
import com.wants.market.user.dto.ProfileRequest;

public interface UserService {

    User signUp(CreateUserRequest createUserRequest);

    void updateUserProfile(ProfileRequest profileRequest);

    void deleteUser();

}
