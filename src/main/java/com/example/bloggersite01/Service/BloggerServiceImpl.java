package com.example.bloggersite01.Service;

import com.example.bloggersite01.Entity.Blog;
import com.example.bloggersite01.Entity.User;
import com.example.bloggersite01.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloggerServiceImpl implements BloggerService {


    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlogger(Long id) throws Exception {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public List<Blog> getBloggers() throws Exception {
        return blogRepository.findAll();
    }

    @Override
    public Blog updateBlogger(Blog blog) throws Exception {
        Blog updateBlog = getBlogger(blog.getId());
        // need to find the blog in the site ...
        if (updateBlog != null) {
            updateBlog.setTitle(blog.getTitle());
            updateBlog.setContent(blog.getContent());
            return blogRepository.save(updateBlog);
        }
        return null;
    }

    @Override
    public Blog addBlogger(Blog blog) throws Exception {
        System.out.println(blog.getUser().getUsername());
        return blogRepository.save(blog);
    }

    @Override
    public void deleteBlogger(Long id) throws Exception {
        blogRepository.deleteById(id);
        // if there is matching od for blogger then that blog will be deleted in here....
    }

    @Override
    public List<Blog> getBlogsOfUser(Long id) throws Exception {
// get blogs for the specified user and user id
        return blogRepository.findBlogsByUser(new User(id));
    }
}
