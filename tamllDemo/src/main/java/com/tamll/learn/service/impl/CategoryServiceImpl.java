package com.tamll.learn.service.impl;

import com.tamll.learn.dao.CategoryMapping;
import com.tamll.learn.entiy.Category;
import com.tamll.learn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类服务层
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private CategoryMapping categoryMapping;

    /**
     * 获取所有分类列表
     * @return 返回一个包含分类的列表
     */
    public List<Category> getAllCategory(){
        return categoryMapping.selectCategoryList();
    }

    /**
     * 通过ID返回分类
     * @param categoryId 分类ID
     * @return 返回分类信息
     */
    public Category getCategoryById(Integer categoryId){
        return categoryMapping.selectCategoryById(categoryId);
    }

    /**
     * 通过分类名返回分类
     * @param categoryName 分类名
     * @return 返回分类信息
     */
    public Category getCategoryByName(String categoryName){
        return categoryMapping.selectCategoryByName(categoryName);
    }
}
