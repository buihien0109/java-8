package com.example.userbackend.controller;

import com.example.userbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class UserViewController {

    private final UserService userService;

    @GetMapping("/")
    public String getUserPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "create";
    }

    @GetMapping("/detail/{id}")
    public String getDetailPage(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("images", userService.getFiles(id));

        return "detail";
    }
}
