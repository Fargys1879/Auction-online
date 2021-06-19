package com.auction.dao;

import com.auction.entity.Category;
import com.auction.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAO implements DAO<Long, Category> {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Category entity) {
        getSession().persist( entity );
    }

    @Override
    public Category findOne(Long key) {
        return getSession().get(Category.class,key);
    }

    @Override
    public Category update(Category entity) {
        return null;
    }

    @Override
    public void delete(Category entity) {

    }

    @Override
    public List<Category> findAll() {
        return null;
    }
}
