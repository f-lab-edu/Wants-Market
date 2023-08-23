package com.wants.market.post.dto;

import com.wants.market.core.viewmodel.PostDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {

    private List<PostDetail> postList;

    private int pageNum;

    public static PostResponse fromPost(List<PostDetail> postList, int pageNum) {
        return PostResponse.builder()
                        .postList(postList)
                        .pageNum(pageNum)
                        .build();
    }


}
