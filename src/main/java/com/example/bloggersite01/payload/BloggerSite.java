package com.example.bloggersite01.payload;

public class BloggerSite {

    private long id ;
    private String title ;
    private String content ;
    private String user_id ;


    public BloggerSite() {}

    public BloggerSite(long id, String title, String content, String user_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user_id = user_id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
