package com.wants.market.post.controller;

import com.wants.market.annotation.LoginValidation;
import com.wants.market.post.dto.CategoryRequest;
import com.wants.market.post.dto.PostRequest;
import com.wants.market.post.dto.PostResponse;
import com.wants.market.post.service.PostService;
import com.wants.market.utils.Responses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    @LoginValidation
    @PostMapping
    public ResponseEntity createPost(@RequestBody @Valid PostRequest postRequest) {
        postService.createPost(postRequest);
        return Responses.CREATED;
    }

    @LoginValidation
    @PutMapping("/{postId}")
    public ResponseEntity updatePost(@RequestBody @Valid PostRequest postRequest, @PathVariable Long postId) {
        Post post = postService.findPostById(postId);
        postService.updatePost(postRequest, post);
        return Responses.RESPONSE_ENTITY_OK;
    }

    @LoginValidation
    @DeleteMapping("/{postId}")
    public ResponseEntity removePost(@PathVariable Long postId) {
        postService.removePost(postId);
        return Responses.RESPONSE_ENTITY_OK;
    }

    @LoginValidation
    @GetMapping("/address")
    public ResponseEntity<PostResponse> getPostByAddress(String address, Integer pageNum) {
        PostResponse response = postService.findPostByAddress(address, pageNum);
        return ResponseEntity.ok(response);
    }

    @LoginValidation
    @GetMapping("/categories")
    public ResponseEntity getPostByAddressAndCategory(String address, CategoryRequest categoryDTO) {
        postService.findPostByAddressAndCategory(address, categoryDTO);
        return Responses.RESPONSE_ENTITY_OK;
    }

}
