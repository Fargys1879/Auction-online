package com.auction.service.impl;

import com.auction.entity.User;
import com.auction.repository.UserRepository;
import com.auction.service.ServiceException;
import com.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Override
    public boolean addNewUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            throw new ServiceException("Exception in addNewUser()",e);
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            throw new ServiceException("Exception in addNewUser()",e);
        }
    }

    @Override
    public boolean removeUser(User user) {
        try {
            Iterable<User> userList = userRepository.findAll();
            for (User u : userList){
                if (u.getId().equals(user.getId())) {
                    userRepository.delete(u);
                    return true;
                }
            }
        } catch (Exception e) {
            throw new ServiceException("Exception in removeUser()",e);
        }
        return false;
    }

    @Override
    public boolean checkUserExist(User user) {
        try {
            Iterable<User> userList = userRepository.findAll();
            for (User u : userList){
                if (u.equals(user)) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new ServiceException("Exception in checkUserExist()",e);
        }
        return false;
    }

    @Override
    public List<User> getAllUserList() {
        List<User> result = new ArrayList<>();
        try {
            Iterable<User> userList = userRepository.findAll();
            for (User u : userList) {
                result.add(u);
            }
        } catch (Exception e) {
            throw new ServiceException("Exception in getAllUserList()",e);
        }
        return result;
    }

    @Override
    public User getUserById(Long id) {
        User result = null;
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()){
                result = userOptional.get();
            }
        } catch (Exception e) {
            throw new ServiceException("Exception in getAllUserList()",e);
        }
        return result;
    }

    @Override
    public User removeUserById(Long id) {
        User result = null;
        try {
            Iterable<User> userList = userRepository.findAll();
            for (User u : userList) {
                if (u.getId().equals(id)) {
                    userRepository.deleteById(id);
                    result = u;
                }
            }
        } catch (Exception e) {
            throw new ServiceException("Exception in removeUserById()",e);
        }
        return result;
    }

    @Override
    public User getUserByLogin(String login) {
        User result = null;
        try {
            Iterable<User> userList = userRepository.findAll();
            for (User u : userList) {
                if (u.getLogin().equals(login)) {
                    result = u;
                }
            }
        } catch (Exception e) {
            throw new ServiceException("Exception in getAllUserList()",e);
        }
        return result;
    }
}
