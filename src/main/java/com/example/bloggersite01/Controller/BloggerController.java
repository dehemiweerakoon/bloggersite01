package com.example.bloggersite01.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BloggerController {
    @GetMapping
    public String getBlogger() {
        return "Blogger How are you doing ?";
    }
}
