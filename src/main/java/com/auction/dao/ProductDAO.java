package com.auction.dao;

import com.auction.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductDAO implements DAO<Long,Product>{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Product entity) {
        getSession().persist( entity );
    }

    @Override
    public Product findOne(Long key) {
        return getSession().get(Product.class,key);
    }

    @Override
    public Product update(Product entity) {
        return (Product) getSession().merge( entity );
    }

    @Override
    public void delete(Product entity) {
        getSession().delete( entity );
    }

    @Override
    public List<Product> findAll() {
        return getSession()
                .createQuery( "from " + Product.class.getName() ).list();
    }
}
