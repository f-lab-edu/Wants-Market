package com.wants.market.utils.geo;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class ReverseGeoData {
    private Status status;
    private List<Address> results;

    @ToString
    @Data
    public static class Status {
        Integer code;
        String name;
        String message;
    }

    @Data
    public static class Address {
        String name;
        Code code;
        Region region;
    }

    @Data
    public static class Code {
        String name;
        String type;
        String mappingId;
    }

    @Data
    public static class Region {
        Area area0;
        Area area1;
        Area area2;
        Area area3;
    }

    @Data
    public static class Area {
        String name;
    }

}
