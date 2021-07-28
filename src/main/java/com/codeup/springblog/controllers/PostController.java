package com.codeup.springblog.controllers;

import com.codeup.springblog.models.*;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller

public class PostController {
    List<Post> posts = new ArrayList<>();

//
//
//    @GetMapping("/posts")
//    public String viewPosts(Model model) {
//        posts.add(new Post("This is post1", "This is post1s body"));
//        posts.add(new Post("This is post2", "This is post2s body"));
//        model.addAttribute("posts", posts);
//        return "posts/index";
//    }
//
//    @GetMapping("/posts/{id}")
//    public String singlePost(@PathVariable long id, Model model) {
//        Post post = new Post("Jeff buys bicycle.", "No one know why. Must really like the feeling of the wind on his face.");
//        model.addAttribute("post", post);
//        return "posts/show";
//    }
//
//    // When you visit the URL you will see the form to create a post.
//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String createForm() {
//        return "View form to create a post.";
//    }
//
//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
//    @ResponseBody
//    public String createPost(){
//       return "create a new post";
//    }

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailSvc;

    public PostController(PostRepository postRepo, UserRepository userDao, EmailService emailSvc) {
        this.postDao = postRepo;
        this.userDao = userDao;
        this.emailSvc = emailSvc;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{n}")
    public String findById(@PathVariable long n, Model model) {
        model.addAttribute("post", postDao.findById(n));
        return "posts/show";
    }

    @PostMapping("/posts/delete/{id}")
    public String deleteById(@PathVariable long id) {
        postDao.deleteById(id);
//        Instructor:
//        postRepo.delete(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String postToEdit(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.findById(id));
        return "posts/edit";
    }

    @PostMapping("/posts/edit/update/{id}")
    public String editPost(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
        Post updatedPost = postDao.getById(id);
        updatedPost.setTitle(title);
        updatedPost.setBody(body);
        postDao.save(updatedPost);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

//    @PostMapping("/posts/create")
//    public String createPost(@RequestParam String title, @RequestParam String body) {
//        User user1 = userDao.getById(1L);
//        Post post = new Post(title, body, user1);
//        postRepo.save(post);
//        return "redirect:/posts";
//    }
@PostMapping("/posts/create")
public String createPost(@ModelAttribute Post post) {
    post.setUser(userDao.getById(1L));
    emailSvc.prepareAndSend(userDao.getById(1L).getEmail(), "New post", "Thank you for creating a new post!");
    postDao.save(post);
    return "redirect:/posts";
}

}