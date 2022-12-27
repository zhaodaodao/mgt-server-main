package com.example.mgtserver.dto;

public class UserProjectDTO {

    private Long id;
    private String username;
    private Integer role;
    private String comment;
    private Integer isDisable;
    /**
     * 创建时间
     */
    private Long gmtCreate;
    /**
     * 最近修改时间
     */
    private Long gmtModify;

    public UserProjectDTO() {
    }

    public UserProjectDTO(Long id, String username, Integer role, String comment, Integer isDisable, Long gmtCreate, Long gmtModify) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.comment = comment;
        this.isDisable = isDisable;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
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

    public Long getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Long gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Override
    public String toString() {
        return "UserProjectDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", comment='" + comment + '\'' +
                ", isDisable=" + isDisable +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                '}';
    }
}
