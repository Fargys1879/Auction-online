package com.auction.service.impl;

import com.auction.dao.UserDAO;
import com.auction.entity.User;
import com.auction.service.ServiceException;
import com.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public boolean addNewUser(User user) {
        try {
            userDAO.save(user);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        try {
            userDAO.update(user);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public boolean removeUser(User user) {
        try {
            userDAO.delete(user);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public List<User> getAllUserList() {
        List<User> result = null;
        try {
            result = userDAO.findAll();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
        return result;
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        User result = null;
        try {
            result = userDAO.findOne(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
        return result;
    }

    @Override
    @Transactional
    public User removeUserById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {
        return null;
    }
}
