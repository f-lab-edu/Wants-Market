package com.wants.market.post.service;

import com.wants.market.core.domain.Post;
import com.wants.market.core.domain.TradeStatus;
import com.wants.market.core.domain.User;
import com.wants.market.core.mapper.PostMapper;
import com.wants.market.core.viewmodel.PostDetail;
import com.wants.market.exception.UnAuthorizedAccessException;
import com.wants.market.post.dto.CategoryRequest;
import com.wants.market.post.dto.PostRequest;
import com.wants.market.post.dto.PostResponse;
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
                .removedAt(null)
                .build();

        postMapper.insertPost(post);
    }

    @Transactional
    public void updatePost(PostRequest postRequest, Long postId) {
        Post post = findPostById(postId);
        validateAuthor(post);
        post.updatePost(postRequest);

        if(postRequest.getCategoryId() != null) {
            String category = categoryService.findCategoryNameById(postRequest.getCategoryId());
            post.updateCategory(category);
        }

        if(postRequest.getTradeStatus() != null && !postRequest.getTradeStatus().isEmpty()) {
            post.updateTradeStatus(postRequest);
        }

        postMapper.updatePost(post);
    }

    public Post findPostById(Long postId) {
        return postMapper.findPostById(postId);
    }

    public void removePost(Long postId) {
        Post post = findPostById(postId);
        validateAuthor(post);
        post.removedAtUpdate();
        postMapper.removedAtUpdate(post);
    }

    public boolean isMatchedAuthor(Post post) {
        User user = sessionService.getLoggedInUserFromDatabase();

        if(!post.getUserId().equals(user.getId())) {
            throw new UnAuthorizedAccessException();
        }

        return true;
    }

    /*
    1. 사용자가 로그인하면 회원정보에 인증한 주소가 있음
    2. 그 인증한 주소 지역의 판매자의 게시물들을 조회할 수 있음 (여러개의 글)
    3. ResponseDTO를 통해 그 게시물들을 사용자에게 응답할 수 있어야함
        -> List<Post> => ResponseDTO 로 타입 변환 해주어야 함
     */
    // 페이징 되는지 테스트 해야함
    public PostResponse findPostByAddress(String address, int pageNum) {
        List<PostDetail> posts = postMapper.findPostByAddress(address, pageNum, (pageNum - 1) * DEFAULT_PAGE_SIZE);
        return PostResponse.fromPost(posts, pageNum);
    }

    public PostResponse findPostByAddressAndCategory(String address, CategoryRequest categoryRequest) {
         List<PostDetail> posts = postMapper.findPostByAddressAndCategory(
                address, categoryRequest.getId(),
                categoryRequest.getPageSize(),
                (categoryRequest.getPageNo() -1) * categoryRequest.getPageSize());
         return PostResponse.fromPost(posts, categoryRequest.getPageNo());
    }
}
