package com.wants.market.post.controller;

import com.wants.market.annotation.LoginValidation;
import com.wants.market.core.domain.Post;
import com.wants.market.post.dto.CategoryDTO;
import com.wants.market.post.dto.PostRequest;
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
        return Responses.RESPONSE_ENTITY_OK;
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
        Post post = postService.findPostById(postId);
        postService.removePost(post);
        return Responses.RESPONSE_ENTITY_OK;
    }

    @LoginValidation
    @GetMapping("/address")
    public ResponseEntity getPostByAddress(String address) {
        postService.findPostByAddress(address);
        return Responses.RESPONSE_ENTITY_OK;
    }

    @LoginValidation
    @GetMapping("/categories")
    public ResponseEntity getPostByAddressAndCategory(String address, CategoryDTO categoryDTO) {
        postService.findPostByAddressAndCategory(address, categoryDTO);
        return Responses.RESPONSE_ENTITY_OK;
    }

}
