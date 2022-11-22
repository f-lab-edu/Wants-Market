package com.wants.market.user.service

import com.wants.market.core.domain.User
import com.wants.market.core.mapper.UserMapper
import com.wants.market.exception.PasswordNotMatchedException
import com.wants.market.exception.UserNotFoundException
import com.wants.market.user.dto.LoginRequest
import com.wants.market.utils.session.SessionKeyConstants
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

import javax.servlet.http.HttpSession

class SessionServiceTest extends Specification {

    UserMapper userMapper = Mock()
    PasswordEncoder passwordEncoder = Mock()
    HttpSession session = Mock()
    SessionService sessionService = new SessionService(userMapper, passwordEncoder, session)
    User user = new User()

    def "로그인 실패 - 존재하는 아이디가 없어 유저를 찾을 수 없는 예외 발생"() {
        given:
        LoginRequest request = new LoginRequest("loginId", "loginPassword")
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
        session.setAttribute(SessionKeyConstants.LOGIN_SESSION,User.builder()
                             .id(1)
                             .userId("loginId")
                             .password("loginPassword1!")
                             .build())

        when:
        sessionService.login(request)

        then:
        noExceptionThrown()
    }

    def "로그아웃 성공" () {
        given:
        session.setAttribute(SessionKeyConstants.LOGIN_SESSION,User.builder()
                .id(1)
                .userId("loginId")
                .password("loginPassword1!")
                .build())

        when:
        sessionService.logoutUser()

        then:
        session.getAttribute(SessionKeyConstants.LOGIN_SESSION) == null
    }
}
