package com.wants.market.post.service;

import com.wants.market.core.domain.Post;
import com.wants.market.core.domain.TradeStatus;
import com.wants.market.core.domain.User;
import com.wants.market.core.mapper.PostMapper;
import com.wants.market.exception.UnAuthorizedAccessException;
import com.wants.market.post.dto.CategoryDTO;
import com.wants.market.post.dto.PostRequest;
import com.wants.market.user.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostMapper postMapper;
    private final CategoryService categoryService;
    private final SessionService sessionService;

    public void createPost(PostRequest postRequest) {
        User user = sessionService.getLoggedInUserFromDatabase();
        String category = categoryService.findCategoryNameById(postRequest.getCategoryId());

        Post post = Post.builder()
                .userId(user.getId())
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .category(category)
                .address(user.getAddress())
                .tradeStatus(TradeStatus.판매중)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        postMapper.insertPost(post);
    }

    public void updatePost(PostRequest postRequest, Post post) {
        if(isMatchedAuthor(post)) {
            post.updatePost(postRequest);
        }

        postMapper.updatePost(post);
    }

    public Post findPostById(Long postId) {
        return postMapper.findPostById(postId);
    }

    public void removePost(Post post) {
        if(isMatchedAuthor(post)) {
            post.removePost();
        }

        postMapper.deletePost(post.getId());
    }

    public boolean isMatchedAuthor(Post post) {
        User user = sessionService.getLoggedInUserFromDatabase();

        if(!post.getUserId().equals(user.getId())) {
            throw new UnAuthorizedAccessException();
        }

        return true;
    }

    public List<Post> findPostByAddress(String address) {
        return postMapper.findPostByAddress(address);
    }

    public List<Post> findPostByAddressAndCategory(String address, CategoryDTO categoryDTO) {
        return postMapper.findPostByAddressAndCategory(
                address, categoryDTO.getId(),
                categoryDTO.getPageSize(),
                (categoryDTO.getPageNo() -1) * categoryDTO.getPageSize());
    }
}
