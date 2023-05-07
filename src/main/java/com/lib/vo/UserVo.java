package com.lib.vo;

public class UserVo {
    private String type;
    private Integer id;
    private String username;
    private String password;

    public UserVo(String type, int id, String username, String password) {
        this.type = type;
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserVo() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
