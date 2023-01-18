package com.wants.market.core.domain;

public enum Category {
    디지털기기(1),
    생활가전(2),
    여성의류(3),
    여성잡화(4),
    남성의류(5),
    남성잡화(6),
    기타중고물품(7);

    private Integer categoryId;

    Category(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }
}