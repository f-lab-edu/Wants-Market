package com.wants.market.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRegionMapper {

    void insertLocation(@Param("regionX") double regionX, @Param("regionY") double regionY,
                        @Param("authStatus") String authStatus, @Param("userId") Long id);

}
