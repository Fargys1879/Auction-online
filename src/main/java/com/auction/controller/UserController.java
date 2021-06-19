package com.auction.controller;

import com.auction.entity.User;
import com.auction.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Data
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        List<User> allUsersList = userService.getAllUserList();
        model.addAttribute("users",allUsersList);
        return "users";
    }

    public void userAddAction(){
        User user = new User("NewUser","NewAdress","Login","pass");
        userService.addNewUser(user);
    }

    public List<User> userGetAllListAction(){
        User user = new User("NewUser","NewAdress","Login","pass");
        List<User> userList = null;
        userService.addNewUser(user);
        userList = userService.getAllUserList();
        return userList;
    }
}
