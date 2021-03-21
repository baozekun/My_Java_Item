package com.bzk.pojo;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/17 14:55
 * @description：用户实体类
 * @modified By：
 */
public class User {
    private String id;
    private String username;
    private String password;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
