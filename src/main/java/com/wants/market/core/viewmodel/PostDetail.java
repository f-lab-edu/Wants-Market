package com.wants.market.core.viewmodel;

import com.wants.market.core.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostDetail extends Post {

    private Long heartCount;
}
