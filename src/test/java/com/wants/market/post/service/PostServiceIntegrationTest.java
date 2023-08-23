package com.wants.market.post.service;

import com.wants.market.core.mapper.PostMapper;
import com.wants.market.post.dto.CategoryRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class PostServiceIntegrationTest {

    @Autowired
    PostService postService;

    @Autowired
    PostMapper postMapper;

    @Test
    void findPostByAddressAndCategory() {
        String address = "석촌동";
        CategoryRequest categoryDTO = CategoryRequest.builder()
                .id(2)
                .pageSize(5)
                .pageNo(2)
                .build();
        int offset = (categoryDTO.getPageNo() - 1) * categoryDTO.getPageSize();

        postService.findPostByAddressAndCategory(address, categoryDTO);

        postMapper.findPostByAddressAndCategory(address, categoryDTO.getId(), categoryDTO.getPageSize(), offset);
    }
}