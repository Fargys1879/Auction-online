package com.auction.service;

import com.auction.entity.User;

import java.util.List;

public interface UserService {
    boolean addNewUser(User user);
    boolean updateUser(User user);
    boolean removeUser(User user);
    boolean checkUserExist(User user);
    List<User> getAllUserList();
    User getUserById(Long id);
    User removeUserById(Long id);
    User getUserByLogin(String login);
}
