package com.wants.market.post.service

import com.wants.market.core.mapper.CategoryMapper
import spock.lang.Specification


class CategoryServiceTest extends Specification {

    CategoryMapper categoryMapper = Mock()
    CategoryService categoryService = new CategoryService(categoryMapper)

    def "카테고리 조회 성공"() {
        given:
        int categoryId = 1

        when:
        categoryService.findCategoryNameById(categoryId)

        then:
        1 * categoryMapper.findCategoryNameById(categoryId)
    }
}