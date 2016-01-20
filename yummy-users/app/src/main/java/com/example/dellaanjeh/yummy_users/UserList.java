package com.example.dellaanjeh.yummy_users;

/**
 * Created by dellaanjeh on 1/19/16.
 */
public class UserList {

    private String username;
    private String name;

    public UserList(String username, String name) {
        super();
        this.username = username;
        this.name = name;
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
}
