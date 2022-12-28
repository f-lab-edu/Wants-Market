package com.wants.market.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ProfileRequest {

    private String password;

    private String nickname;

    private String phone;

    private String address;

}
