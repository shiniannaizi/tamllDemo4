package com.tamll.learn.service;

import com.tamll.learn.dao.CategoryMapping;
import com.tamll.learn.entiy.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类服务层
 */
@Service
public class CategoryService {

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

    public Category getCategoryById(Integer categoryId){
        return categoryMapping.selectCategoryById(categoryId);
    }
}
