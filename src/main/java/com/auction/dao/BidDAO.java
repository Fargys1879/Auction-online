package com.auction.dao;

import com.auction.entity.Bid;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BidDAO implements DAO<Long, Bid>{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Bid entity) {
        getSession().persist( entity );
    }

    @Override
    public Bid findOne(Long key) {
        return getSession().get(Bid.class,key);
    }

    @Override
    public Bid update(Bid entity) {
        return null;
    }

    @Override
    public void delete(Bid entity) {

    }

    @Override
    public List<Bid> findAll() {
        return null;
    }
}
