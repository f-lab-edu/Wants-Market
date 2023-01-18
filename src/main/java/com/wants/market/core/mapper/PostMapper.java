package com.wants.market.core.mapper;

import com.wants.market.core.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    Post findPostById(Long id);

    List<Post> findPostByAddress(String address);

    List<Post> findPostByAddressAndCategory(String address, int categoryId, int pageSize, int offset);

    void insertPost(Post post);

    void updatePost(Post post);

    void deletePost(Long id);

}