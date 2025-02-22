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

    public Blog() {
    }
    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
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
