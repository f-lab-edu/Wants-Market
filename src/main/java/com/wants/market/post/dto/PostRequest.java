package com.wants.market.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostRequest {

    @NotBlank
    @Length(max = 100, message = "제목은 최대 100글자를 넘을 수 없습니다.")
    private String title;

    @NotBlank
    private String content;

    private Integer categoryId;
}
