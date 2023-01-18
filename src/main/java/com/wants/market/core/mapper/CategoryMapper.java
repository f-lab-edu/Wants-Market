package com.wants.market.core.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    String findCategoryNameById(int categoryId);
}
