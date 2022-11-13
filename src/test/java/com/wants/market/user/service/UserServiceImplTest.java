package com.wants.market.user.service;

import com.wants.market.core.domain.User;
import com.wants.market.user.dto.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    void userSignUpTest() throws Exception {
        //given
        String userId = "test123";
        String password = "test1234@";
        String name = "test";
        String nickname = "test";
        String phone = "010-1234-5678";
        String email = "test@com";

        CreateUserRequest createUserRequest = new CreateUserRequest(
                userId, password, name, nickname, phone, email);

        //when
         User result = userService.signUp(createUserRequest);

        //then
        Assertions.assertNotNull(result);
    }

    @Test
    void user_예외() throws Exception {
        String userId = "test123";
        String password = "test1234@";
        String name = "test";
        String nickname = "test";
        String phone = "010-1234-5678";
        String email = "test@com";

        CreateUserRequest createUserRequest = new CreateUserRequest(
                userId, password, name, nickname, phone, email);

        try {
            userService.signUp(createUserRequest);
        } catch(Exception e) {
            System.out.println("오류 발생");
            e.printStackTrace();
        }
    }

}