package com.example.thymeleafdemo.controller;

import com.example.thymeleafdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("user", userService.getUserById(1));
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("/about")
    public String getAboutPage(Model model) {
        model.addAttribute("age", 17);
        model.addAttribute("day", 7);
        model.addAttribute("user", userService.getUserById(2));
        model.addAttribute("users", userService.getUsers());
        return "about";
    }

    @GetMapping("/contact")
    public String getContactPage(Model model) {
        model.addAttribute("user", userService.getUserById(3));
        model.addAttribute("users", userService.getUsers());
        return "contact";
    }
}
