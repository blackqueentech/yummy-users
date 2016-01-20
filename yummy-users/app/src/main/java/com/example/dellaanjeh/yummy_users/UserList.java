package com.example.dellaanjeh.yummy_users;

/**
 * Created by dellaanjeh on 1/19/16.
 */
public class UserList {

    private String username;
    private String name;
    private String email;
    private String imageUrl;
    private String title;
    public UserList(String username, String name, String email, String imageUrl, String title) {
        super();
        this.username = username;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
