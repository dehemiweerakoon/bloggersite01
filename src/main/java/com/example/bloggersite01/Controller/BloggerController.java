package com.example.bloggersite01.Controller;

import com.example.bloggersite01.Entity.Blog;
import com.example.bloggersite01.Repository.BlogRepository;
import com.example.bloggersite01.Service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
public class BloggerController {


    @Autowired
    private BloggerService bloggerService;

    @GetMapping
    public ResponseEntity<?> getBlogger() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bloggerService.getBloggers());
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogger(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bloggerService.getBlogger(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createBlogger(@RequestBody Blog blog) {
        try {
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
}
