package com.example.bloggersite01.Repository;

import com.example.bloggersite01.Entity.Blog;
import com.example.bloggersite01.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
    // add the custom queries
    // in here we must add the custom queries
    List<Blog> findBlogsByUser(User user);
}
