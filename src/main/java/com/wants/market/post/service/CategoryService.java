package com.wants.market.post.service;

import com.wants.market.core.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public String findCategoryNameById(int categoryId) {
        return categoryMapper.findCategoryNameById(categoryId);
    }

}
