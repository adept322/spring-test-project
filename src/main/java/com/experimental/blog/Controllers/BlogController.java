package com.experimental.blog.Controllers;

import com.experimental.blog.DAO.PostDAO;
import com.experimental.blog.Models.PostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostDAO postDAO;

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<PostModel> posts = postDAO.findAll();
        model.addAttribute("posts", posts);
        return "blog/blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog/blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        PostModel postModel = new PostModel(title, anons, full_text);
        postDAO.save(postModel);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postDAO.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<PostModel> post = postDAO.findById(id);
        ArrayList<PostModel> result = new ArrayList<>();
        post.ifPresent(result::add);
        model.addAttribute("post", result);
        return "blog/blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postDAO.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<PostModel> post = postDAO.findById(id);
        ArrayList<PostModel> result = new ArrayList<>();
        post.ifPresent(result::add);
        model.addAttribute("post", result);
        return "blog/blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        PostModel postModel = postDAO.findById(id).orElseThrow();
        postModel.setTitle(title);
        postModel.setAnons(anons);
        postModel.setFull_text(full_text);
        postDAO.save(postModel);
        return "redirect:/blog/";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model) {
        PostModel postModel = postDAO.findById(id).orElseThrow();
        postDAO.delete(postModel);
        return "redirect:/blog/";
    }
}
