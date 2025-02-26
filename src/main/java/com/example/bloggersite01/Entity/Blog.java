package com.example.bloggersite01.Entity;

import jakarta.persistence.*;


@Entity
public class Blog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false,unique = true)
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;
    //

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Blog() {
        // blog basic construct...
    }

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Blog(long id, String title, String content, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Blog(long blogId) {
        this.id = blogId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
