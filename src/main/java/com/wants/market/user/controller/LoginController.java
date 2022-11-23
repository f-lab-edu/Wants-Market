package com.wants.market.user.controller;

import com.wants.market.user.dto.LoginRequest;
import com.wants.market.user.service.SessionService;
import com.wants.market.utils.Responses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final SessionService sessionService;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest) {
        sessionService.login(loginRequest);
        return Responses.RESPONSE_ENTITY_OK;
    }

    @PostMapping("/logout")
    public ResponseEntity logout() {
        sessionService.logoutUser();
        return Responses.RESPONSE_ENTITY_OK;
    }
}
