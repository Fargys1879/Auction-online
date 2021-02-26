package dao;

import entity.User;

import java.util.List;

public interface UserDAO {
    //create
    boolean addUser(User user);

    //read
    List<User> getUserList();
    User getUserById(Long id);
    List<User> getUserByUserName(String username);
    User getUserByLogin(String login);

    //updateUser
    boolean updateUserById(Long id, User newUser);

    //delete
    void removeUserById(Long id);
}
