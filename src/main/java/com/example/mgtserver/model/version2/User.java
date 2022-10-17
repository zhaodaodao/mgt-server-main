package com.example.mgtserver.model.version2;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-06 11:28
 */
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer role;
    private String comment;
    private Integer isDisable;
    private Long gmtCreate;
    private Long gmtLastLogin;

    public User() {}

    /**
     * 用户
     * @param id           id
     * @param username     用户名
     * @param password     密码
     * @param role         角色
     * @param comment      备注
     * @param isDisable    是否被禁用
     * @param gmtCreate    创建时间
     * @param gmtLastLogin 上次登录时间
     */
    public User(Long id, String username, String password, Integer role, String comment, Integer isDisable, Long gmtCreate, Long gmtLastLogin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.comment = comment;
        this.isDisable = isDisable;
        this.gmtCreate = gmtCreate;
        this.gmtLastLogin = gmtLastLogin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Integer isDisable) {
        this.isDisable = isDisable;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtLastLogin() {
        return gmtLastLogin;
    }

    public void setGmtLastLogin(Long gmtLastLogin) {
        this.gmtLastLogin = gmtLastLogin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", comment='" + comment + '\'' +
                ", isDisable=" + isDisable +
                ", gmtCreate=" + gmtCreate +
                ", gmtLastLogin=" + gmtLastLogin +
                '}';
    }
}
