package com.auction.controller;

import com.auction.entity.User;
import com.auction.entity.UserBid;
import com.auction.service.BidService;
import com.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BidService bidService;

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        List<User> allUsersList = userService.getAllUserList();
        model.addAttribute("users",allUsersList);
        return "users";
    }

    @PostMapping("/users")
    public String postUsersPage(Model model, @RequestParam String userId) {
        Long id = Long.parseLong(userId);
        List<UserBid> userBids = bidService.getBidsByUserId(id);
        User user = userService.getUserById(id);
        model.addAttribute("userBids",userBids);
        model.addAttribute("userLogin",user.getLogin());
        return "userBids";
    }

    @GetMapping("/user/{id}")
    public String getUserDetailsPage(Model model,
                                     @PathVariable(value = "id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-details";
    }
}
