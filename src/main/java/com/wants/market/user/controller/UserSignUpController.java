package com.wants.market.user.controller;

import com.wants.market.user.dto.CreateUserRequest;
import com.wants.market.user.service.UserServiceImpl;
import com.wants.market.utils.Responses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserSignUpController {

    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity signUp(@Valid @RequestBody CreateUserRequest createUserRequest) {
        userService.signUp(createUserRequest);
        return Responses.CREATED;
    }
}
