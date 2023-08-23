package com.wants.market.core.mapper;

import com.wants.market.core.domain.Post;
import com.wants.market.core.viewmodel.PostDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    Post findPostById(Long id);

    List<PostDetail> findPostByAddress(String address, int pageSize, int offset);

    List<PostDetail> findPostByAddressAndCategory(String address, int categoryId, int pageSize, int offset);

    void insertPost(Post post);

    void removedAtUpdate(Post post);

    void updatePost(Post post);

}