package com.wants.market.block.controller;

import com.wants.market.block.dto.BlockList;
import com.wants.market.block.dto.CreateBlock;
import com.wants.market.block.service.BlockService;
import com.wants.market.utils.Responses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/blocks")
@RequiredArgsConstructor
@RestController
public class BlockController {

    private final BlockService blockService;

    @PostMapping
    public ResponseEntity<Void> blockUser(@RequestBody CreateBlock createBlock) {
        blockService.blockUser(createBlock);
        return Responses.CREATED;
    }

    @DeleteMapping
    public ResponseEntity<Void> unblockUser(@RequestBody CreateBlock createBlock) {
        blockService.unblockUser(createBlock);
        return Responses.RESPONSE_ENTITY_OK;
    }

    @GetMapping
    public ResponseEntity<List<BlockList>> getBlockUserList() {
        return ResponseEntity.ok(blockService.blockedList());
    }
}
