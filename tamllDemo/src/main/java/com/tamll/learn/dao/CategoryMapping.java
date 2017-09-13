package com.tamll.learn.dao;

import com.tamll.learn.entiy.Category;

import java.util.List;

/**
 * Category数据库操作接口
 */
public interface CategoryMapping {

    int insert(Category category);

    List<Category> selectCategoryList();

    int delete(Integer categoryId);

    Category selectCategoryById(Integer categoryId);

    Category selectCategoryByName(String categoryName);

}
