package com.codeup.springblog.controllers;

import com.codeup.springblog.models.AdRepository;
import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {
    private final AdRepository adDao;

    public AdController(AdRepository adDao) {
        this.adDao = adDao;
    }

    @GetMapping("/ads")
    public String index(Model model) {
        model.addAttribute("ads", adDao.findAll());
        return "ads/index";
    }
    @GetMapping("/ads.{n")
    public String viewOne(@PathVariable long n, Model model) {
        model.addAttribute("ad", adDao.findOne(n));
        return "ads/show";
    }
}
