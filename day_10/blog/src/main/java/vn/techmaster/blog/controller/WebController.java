package vn.techmaster.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {
    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/blogs/{id}/{slug}")
    public String getDetailPage(@PathVariable String id) {
        return "detail";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }

    @GetMapping("/contact")
    public String getContactPage() {
        return "contact";
    }
}
