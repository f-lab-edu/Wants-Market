package com.wants.market.heart.controller;

import com.wants.market.annotation.LoginValidation;
import com.wants.market.heart.dto.CreateHeart;
import com.wants.market.heart.service.PostHeartService;
import com.wants.market.utils.Responses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/heart")
@RestController
public class PostHeartController {

    private final PostHeartService postHeartService;

    @LoginValidation
    @PostMapping
    public ResponseEntity createHeart(@RequestBody CreateHeart createHeart) {
        postHeartService.createHeart(createHeart);
        return Responses.CREATED;
    }

    @LoginValidation
    @DeleteMapping
    public ResponseEntity deleteHeart(@RequestBody CreateHeart createHeart) {
        postHeartService.deleteHeart(createHeart);
        return Responses.RESPONSE_ENTITY_OK;
    }

    @LoginValidation
    @GetMapping
    public int findHeart(CreateHeart createHeart) {
        return postHeartService.findHeart(createHeart);
    }
}
