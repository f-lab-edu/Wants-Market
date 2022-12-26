package com.wants.market.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateUserRequest {

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$"
             ,message = "영문, 특수문자, 숫자를 모두 한자리씩 포함한 문자를 입력해주세요.")
    private String password;

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    private String nickname;

    @NotBlank(message = "전화번호는 필수 입력값입니다.")
    private String phone;

    @NotBlank(message = "주소는 필수 입력값입니다.")
    private String address;

    @NotBlank
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

}
