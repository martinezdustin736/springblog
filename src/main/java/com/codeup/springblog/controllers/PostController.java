package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostRepository;
import com.codeup.springblog.models.User;
import com.codeup.springblog.models.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    private final PostRepository postRepo;
    private final UserRepository userDao;

    public PostController(PostRepository postRepo, UserRepository userDao) {
        this.postRepo = postRepo;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{n}")
    public String findById(@PathVariable long n, Model model) {
        model.addAttribute("post", postRepo.findById(n));
        return "posts/show";
    }

    @PostMapping("/posts/delete/{id}")
    public String deleteById(@PathVariable long id) {
        postRepo.deleteById(id);
//        Instructor:
//        postRepo.delete(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String postToEdit(@PathVariable long id, Model model) {
        model.addAttribute("post", postRepo.findById(id));
        return "posts/edit";
    }

    @PostMapping("/posts/edit/update/{id}")
    public String editPost(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
        Post updatedPost = postRepo.getById(id);
        updatedPost.setTitle(title);
        updatedPost.setBody(body);
        postRepo.save(updatedPost);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String showCreateForm() {
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam String title, @RequestParam String body) {
        User user1 = userDao.getById(1L);
        Post post = new Post(title, body, user1);
        postRepo.save(post);
        return "redirect:/posts";
    }
}