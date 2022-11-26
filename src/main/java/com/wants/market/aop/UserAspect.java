package com.wants.market.aop;

import com.wants.market.exception.UserNotFoundException;
import com.wants.market.user.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@RequiredArgsConstructor
@Configuration
@Aspect
public class UserAspect {

    private final SessionService sessionService;

    @Before("@annotation(com.wants.market.annotation.LoginValidation)")
    public void validationLogin() {
        Optional.ofNullable(sessionService.getLoggedInUserFromSession())
                .orElseThrow( ()-> new UserNotFoundException("유저를 찾을 수 없습니다."));
    }
}
