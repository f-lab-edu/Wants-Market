package com.wants.market.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CategoryDTO {

    private int id;

    private int pageSize;

    private int pageNo;
}
