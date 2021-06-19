package com.auction.dao;

import com.auction.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDAO implements DAO<Long, User> {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(User entity) {
        getSession().persist( entity );
    }

    @Override
    public User findOne(Long key) {
        return getSession().get(User.class,key);
    }

    @Override
    public User update(User entity) {
        return (User) getSession().merge( entity );
    }

    @Override
    public void delete(User entity) {
        getSession().delete( entity );
    }

    @Override
    public List<User> findAll() {
        return getSession()
                .createQuery( "from " + User.class.getName() ).list();
    }

    public User getUserByLogin(String login) {
        return (User) getSession().createQuery("u from"+ User.class.getName() +"where u.login = login");
    }
}
