package com.auction.service;

import com.auction.entity.Category;

public interface CategoryService {
    boolean addCategory(Category category);
    Category getCategoryById(Long id);
    boolean deleteCategory(Category category);
}
