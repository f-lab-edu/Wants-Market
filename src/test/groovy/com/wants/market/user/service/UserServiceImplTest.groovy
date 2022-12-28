package com.wants.market.user.service

import com.wants.market.core.domain.User
import com.wants.market.core.mapper.UserMapper
import com.wants.market.exception.DuplicatedIdException
import com.wants.market.user.dto.CreateUserRequest
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

class UserServiceImplTest extends Specification {

    UserMapper userMapper = Mock()
    PasswordEncoder passwordEncoder = Mock()
    SessionService sessionService = Mock()
    UserServiceImpl userService = new UserServiceImpl(userMapper, passwordEncoder, sessionService)

    def "회원가입 테스트 - 성공(존재하지 않는 이메일로 가입 시도)"() {

        given:
        CreateUserRequest request = new CreateUserRequest("testId", "testPassword1!", "testName",
                "testAddress", "testNickName", "010-1234-5678", "test.com")
        userMapper.isExistsUserId("testId") >> false
        passwordEncoder.encode("testPassword1!") >> "hashedPassword"

        when:
        User result = userService.signUp(request)

        then:
        println result
        result != null
        result.getUserId() == "testId"
        result.getPassword() == "hashedPassword"
        result.getName() == "testName"
    }

    def "회원가입 테스트 - 실패(존재하는 이메일로 가입 시도)"() {
        given:
        CreateUserRequest request = new CreateUserRequest("testId", "testPassword1!", "testName",
                "testAddress", "testNickName", "010-1234-5678", "test.com")
        userMapper.isExistsUserId("testId") >> true
        passwordEncoder.encode("testPassword1!") >> "hashedPassword"

        when:
        userService.signUp(request)

        then:
        thrown(DuplicatedIdException)
    }


    def "회원가입 한 유저 삭제 성공" () {
        given:
        User user = new User()
        sessionService.getLoggedInUserFromDatabase() >> user

        when:
        userService.deleteUser()

        then:
        userMapper.deleteUser(user.getId())
    }
}
