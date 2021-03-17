package com.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private Environment env;
    @Value("${auction.title}")
    String title;

    @GetMapping("/")
    String getIndexPage(Model model) {
        String hostName = env.getProperty("auction.hostName");
        model.addAttribute("hostName",hostName);
        model.addAttribute("title",title);
        return "index";
    }
}
