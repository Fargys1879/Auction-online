package com.auction.service.impl;

import com.auction.dao.CategoryDAO;
import com.auction.entity.Category;
import com.auction.service.CategoryService;
import com.auction.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    @Override
    @Transactional
    public boolean addCategory(Category category) {
        try {
            categoryDAO.save(category);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public Category getCategoryById(Long id) {
        Category category = null;
        try {
            category = categoryDAO.findOne(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
        return category;
    }

    @Override
    @Transactional
    public boolean deleteCategory(Category category) {
        try {
            categoryDAO.delete(category);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
