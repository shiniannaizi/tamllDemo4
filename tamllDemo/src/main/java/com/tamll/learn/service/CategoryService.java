package com.tamll.learn.service;

import com.tamll.learn.entiy.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAllCategory();

    public Category getCategoryById(Integer categoryId);

    public Category getCategoryByName(String categoryName);
}
