package com.example.bloggersite01.Service;

import com.example.bloggersite01.Entity.Blog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BloggerService {

    Blog getBlogger(Long id) throws Exception;
    List<Blog> getBloggers()throws Exception;
    Blog updateBlogger(Blog blog)  throws Exception;
    Blog addBlogger(Blog blog) throws Exception;
    void deleteBlogger(Long id) throws Exception;
}
