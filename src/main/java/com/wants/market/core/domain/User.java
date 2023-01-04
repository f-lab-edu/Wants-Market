package com.wants.market.core.domain;

import com.wants.market.user.dto.CreateUserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User implements Serializable {

    private Long id;

    private String userId;

    private String password;

    private String name;

    private String nickname;

    private String phone;

    private String email;

    private String address;

    private String profileImage;

    private Agree agreeTerms;

    private Agree marketingTerms;

    private BigDecimal mannerTemp;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static User create(CreateUserRequest createUserRequest,
                              String hashedPassword) {

        User user = new User();
        user.userId = createUserRequest.getUserId();
        user.password = hashedPassword;
        user.name = createUserRequest.getName();
        user.nickname = createUserRequest.getNickname();
        user.phone = createUserRequest.getPhone();
        user.email = createUserRequest.getEmail();
        user.address = createUserRequest.getAddress();
        user.agreeTerms = Agree.Y;
        user.marketingTerms = Agree.N;
        user.mannerTemp = new BigDecimal("36.5");
        user.createdAt = LocalDateTime.now();
        user.updatedAt = LocalDateTime.now();

        return user;
    }

}
