package com.wants.market.user.controller;

import com.wants.market.annotation.LoginValidation;
import com.wants.market.user.dto.CreateUserRequest;
import com.wants.market.user.dto.ProfileRequest;
import com.wants.market.user.service.UserServiceImpl;
import com.wants.market.utils.Responses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity signUp(@Valid @RequestBody CreateUserRequest createUserRequest) {
        userService.signUp(createUserRequest);
        return Responses.CREATED;
    }

    @LoginValidation
    @PutMapping("/my-profiles")
    public ResponseEntity updateUserProfile(@RequestBody ProfileRequest profileRequest) {
        userService.updateUserProfile(profileRequest);
        return Responses.RESPONSE_ENTITY_OK;
    }

    @LoginValidation
    @DeleteMapping
    public ResponseEntity deleteUser() {
        userService.deleteUser();
        return Responses.RESPONSE_ENTITY_OK;
    }
}
