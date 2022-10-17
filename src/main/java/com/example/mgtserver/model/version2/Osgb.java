package com.example.mgtserver.model.version2;

/**
 * @author Hexrt
 * @description Osgb倾斜摄影模型需求版本2
 * @create 2022-04-08 19:53
 */
public class Osgb {
    private Long id;
    private Long projectId;
    private String name;
    private String url;
    private String description;
    private Integer isChecked;
    private Integer isDeleted;
    private Long gmtCreate;
    private Long gmtModify;

    public Osgb() {
    }

    /**
     * osgb模型
     * @param id          模型id
     * @param projectId   所属项目id
     * @param name        模型名称
     * @param url         模型资源地址
     * @param description 模型介绍
     * @param isChecked   默认加载
     * @param isDeleted   是否删除
     * @param gmtCreate   创建时间
     * @param gmtModify   修改更新时间
     */
    public Osgb(Long id, Long projectId, String name, String url, String description, Integer isChecked, Integer isDeleted, Long gmtCreate, Long gmtModify) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.url = url;
        this.description = description;
        this.isChecked = isChecked;
        this.isDeleted = isDeleted;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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
        return "Osgb{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", isChecked=" + isChecked +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                '}';
    }
}
