package com.example.bloggersite01.Controller;

import com.example.bloggersite01.Entity.Blog;
import com.example.bloggersite01.Service.BloggerService;
import com.example.bloggersite01.payload.BloggerSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/blog")
public class BloggerController {
    @Autowired
    private BloggerService bloggerService;

    @GetMapping
    public ResponseEntity<?> getBlogger() {
        try {
            List<BloggerSite> bloggerList = new ArrayList<>();
            List<Blog> blogs = bloggerService.getBloggers();
            for (Blog blog : blogs) {
                bloggerList.add(new BloggerSite(blog.getId(),blog.getTitle(),blog.getContent(),blog.getUser().getUsername()));
            }
            return ResponseEntity.status(HttpStatus.OK).body(bloggerList);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogger(@PathVariable Long id) {
        try {
           Blog blog =   bloggerService.getBlogger(id);

            return ResponseEntity.status(HttpStatus.OK).body(new BloggerSite(blog.getId(),blog.getTitle(),blog.getContent(),blog.getUser().getUsername()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createBlogger(@RequestBody Blog blog) {
        try {
            System.out.println(blog.getUser());
            return ResponseEntity.status(HttpStatus.CREATED).body(bloggerService.addBlogger(blog));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlogger(@PathVariable Long id) {
        try {
            bloggerService.deleteBlogger(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted the blog");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PutMapping()
    public ResponseEntity<?> updateBlogger(@RequestBody Blog blog) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bloggerService.updateBlogger(blog));
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserBlogger(@PathVariable Long id) {
        try {
            List<BloggerSite> bloggerList = new ArrayList<>();
            List<Blog> blogs = bloggerService.getBlogsOfUser(id);
            for (Blog blog : blogs) {
                bloggerList.add(new BloggerSite(blog.getId(),blog.getTitle(),blog.getContent(),blog.getUser().getUsername()));
            }
            return ResponseEntity.status(HttpStatus.OK).body(bloggerList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
