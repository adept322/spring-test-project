package com.experimental.blog.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String blog(Model model) {
        return "blog/blog-main";
    }
    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }
}
