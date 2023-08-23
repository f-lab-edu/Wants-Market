package com.wants.market.core.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Evaluation {

    GOOD_MANNER("good manner"),
    GOODS_GOOD("goods good"),
    SET_TIME("set time"),
    FAST_RESPONSE("fast response"),
    GOODS_DETAIL("goods detail"),
    GOODS_SHARE("goods share"),
    SELL_LOW_PRICE("sell low price");

    private String description;

    @JsonCreator
    public static Evaluation fromString(String value) {
        for (Evaluation evaluation : Evaluation.values()) {
            if (evaluation.name().equalsIgnoreCase(value)) {
                return evaluation;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found");
    }
}
