package service;

import dao.UserDAO;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean addNewUser(User userToAdd) {
        if (userToAdd != null) {
            return userDAO.addUser(userToAdd);
        }
        throw new NullPointerException();
    }

    public boolean checkUserPresence(User userToFind){
        List<User> findUserList = userDAO.getUserByUserName(userToFind.getUserName());
        if (findUserList == null) {
            throw new NullPointerException();
        }
        return true;
    }

    public List<User> getAllUserList() {
        List<User> users = userDAO.getUserList();
        if (users != null) {
            return users;
        }
        throw new NullPointerException();
    }

    public List<User> getUserByName(String userName) {
        List<User> users = userDAO.getUserList();
        List<User> result = new ArrayList<>();
        users.stream().filter(user -> user.getUserName().equals(userName))
                    .forEach(result::add);
        if (result.isEmpty()) {
            throw new NullPointerException();
        }
        return result;
    }

}
