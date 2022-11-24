package com.wants.market.user.service

import com.wants.market.core.domain.User
import com.wants.market.core.mapper.UserMapper
import com.wants.market.exception.UserNotFoundException
import com.wants.market.user.dto.LoginRequest
import com.wants.market.utils.session.SessionKeyConstants
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

import javax.servlet.http.HttpSession

class SessionServiceTest extends Specification {

    UserMapper userMapper = Mock()
    PasswordEncoder passwordEncoder = Mock()
    HttpSession httpSession = Mock()
    SessionService sessionService = new SessionService(userMapper, passwordEncoder, httpSession)
    User user = new User()

    def "로그인 실패 - 존재하는 아이디가 없어 유저를 찾을 수 없는 예외 발생"() {
        given:
        LoginRequest request = new LoginRequest("loginId", "loginPassword1!")
        userMapper.findUserByLoginId("loginId") >> null

        when:
        sessionService.login(request)

        then:
        thrown(UserNotFoundException)
    }

    def "로그인 성공"() {
        given:
        LoginRequest request = new LoginRequest("loginId", "loginPassword1!")
        userMapper.findUserByLoginId("loginId") >> user
        passwordEncoder.matches("loginPassword1!", user.getPassword()) >> true
        httpSession.setAttribute(SessionKeyConstants.LOGIN_SESSION, User.builder()
                                                                    .id(1)
                                                                    .userId("LoginId")
                                                                    .password("password")
                                                                    .build())

        when:
        sessionService.login(request)

        then:
        noExceptionThrown()
    }

    def "로그아웃 성공"() {
        given:
        httpSession.setAttribute(SessionKeyConstants.LOGIN_SESSION, User.builder()
                .id(1)
                .userId("LoginId")
                .password("password")
                .build())

        when:
        sessionService.logoutUser()

        then:
        httpSession.getAttribute(SessionKeyConstants.LOGIN_SESSION) == null
    }

}
