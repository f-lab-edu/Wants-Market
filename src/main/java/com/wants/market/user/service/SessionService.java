package com.wants.market.user.service;

import com.wants.market.core.domain.User;
import com.wants.market.core.mapper.UserMapper;
import com.wants.market.exception.PasswordNotMatchedException;
import com.wants.market.exception.UserNotFoundException;
import com.wants.market.user.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.wants.market.utils.session.SessionKeyConstants.LOGIN_SESSION;

@RequiredArgsConstructor
@Service
public class SessionService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    public void login(LoginRequest loginRequest) {

        User user = userMapper.findUserByLoginId(loginRequest.getLoginId());
        validateExistUser(user);

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchedException("비밀번호가 일치하지 않습니다.");
        }

        httpSession.setAttribute(LOGIN_SESSION, User.builder()
                .id(user.getId())
                .userId(loginRequest.getLoginId())
                .password(loginRequest.getPassword())
                .build());
    }

    private void validateExistUser(User user) {
        Optional.ofNullable(user)
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
    }

    public User getLoggedInUserFromSession() {
        return (User) Optional.ofNullable(httpSession.getAttribute(LOGIN_SESSION))
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
    }

    public User getLoggedInUserFromDatabase() {
        User user = (User) Optional.ofNullable(httpSession.getAttribute(LOGIN_SESSION))
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

        return userMapper.findUserById(user.getId());
    }

    public void logoutUser() {
        httpSession.removeAttribute(LOGIN_SESSION);
    }

}