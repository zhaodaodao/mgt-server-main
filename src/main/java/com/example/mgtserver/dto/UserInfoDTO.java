package com.example.mgtserver.dto;

import com.example.mgtserver.model.version2.User;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-11 13:38
 */
public class UserInfoDTO {
    private Long id;
    private String username;
    private Integer role;
    private String comment;
    private Integer isDisable;
    private Long gmtCreate;
    private Long gmtLastLogin;

    public UserInfoDTO() {
    }

    public UserInfoDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.comment = user.getComment();
        this.isDisable = user.getIsDisable();
        this.gmtCreate = user.getGmtCreate();
        this.gmtLastLogin = user.getGmtLastLogin();
    }

    public UserInfoDTO(Long id, String username, Integer role, String comment, Integer isDisable, Long gmtCreate, Long gmtLastLogin) {
        this.id = id;
        this.username = username;
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
        return "UserInfoDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", comment='" + comment + '\'' +
                ", isDisable=" + isDisable +
                ", gmtCreate=" + gmtCreate +
                ", gmtLastLogin=" + gmtLastLogin +
                '}';
    }
}
