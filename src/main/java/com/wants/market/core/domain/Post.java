package com.wants.market.core.domain;

import com.wants.market.post.dto.PostRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Post {

    private Long id;

    private Long userId;

    private String address;

    private String title;

    private String content;

    private String itemName;

    private BigDecimal itemPrice;

    private TradeStatus tradeStatus;

    private String category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean removed = false;

    public void updatePost(PostRequest postRequest) {
        this.title = postRequest.getTitle();
        this.content = postRequest.getContent();
    }

    public void removePost() {
        removed = true;
    }

    public void updateCategory(String category) {
        this.category = category;
    }

    public void updateTradeStatus(PostRequest postRequest) {
        TradeStatus status = TradeStatus.valueOf(postRequest.getTradeStatus());
        this.tradeStatus = status;
    }

}