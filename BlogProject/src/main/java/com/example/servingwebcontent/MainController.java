package com.example.servingwebcontent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog/add")
    public String add(Model model) {

        return "blog-add";
    }

    @GetMapping("/test")
    public String test(Model model) {

        return "test";
    }


    @PostMapping("/blog/add")
    public String addPost(@RequestParam String title, @RequestParam String description, Model model){
        Post post = new Post();
        post.setTitle(title);
        post.setDescription(description);
        postRepository.save(post);

        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}/delete")
    public String deletePost(@PathVariable int id){
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            postRepository.deleteById(id);
            return "redirect:/blog";
        }
        return "null";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "Даня");
        return "home";
    }

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> postIterable = postRepository.findAll();
        model.addAttribute("title", "Блог сайта");
        model.addAttribute("posts", postIterable);
        return "blog-main";
    }

    @GetMapping("/blog/{id}")
    public String getPostById(@PathVariable int id, Model model){
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = optionalPost.get();
        model.addAttribute("title", post.getTitle());
        model.addAttribute("description", post.getDescription());
        return "blog-item";
    }

    @GetMapping("/about")
    public String aboutMain(Model model) {
        model.addAttribute("title", "Станица о нас");
        return "about";
    }
}