package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostRepository;
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

    public PostController(PostRepository postRepo){
        this.postRepo = postRepo;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{n}")
    public String findById(@PathVariable long n, Model model){
        model.addAttribute("post", postRepo.findById(n));
        return "posts/show";
    }

    @PostMapping("/posts/save/edit/{id}")
    public String editOne(Model model,@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body){
        Post post = postRepo.getById(id);
        post.setTitle(title);
        post.setBody(body);
        postRepo.save(post);
        return "redirect:/posts/" + post.getById();
    }

    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id, Model model) {
        postRepo.delete(postRepo.deletePostById(id));
        return "redirect:/posts";
    }

}
