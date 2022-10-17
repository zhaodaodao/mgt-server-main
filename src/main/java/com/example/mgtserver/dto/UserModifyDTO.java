package com.example.mgtserver.dto;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-06 19:24
 */
public class UserModifyDTO {
    private Long id;
    private String username;
    private Integer role;
    private String comment;
    private Integer isDisable;
    private Long gmtCreate;
    private Long gmtLastLogin;
    private String token;

    public UserModifyDTO() {
    }

    public UserModifyDTO(Long id, String username, Integer role, String comment, Integer isDisable, Long gmtCreate, Long gmtLastLogin, String token) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.comment = comment;
        this.isDisable = isDisable;
        this.gmtCreate = gmtCreate;
        this.gmtLastLogin = gmtLastLogin;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserModifyDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", comment='" + comment + '\'' +
                ", isDisable=" + isDisable +
                ", gmtCreate=" + gmtCreate +
                ", gmtLastLogin=" + gmtLastLogin +
                ", token='" + token + '\'' +
                '}';
    }
}
