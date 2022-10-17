package com.example.mgtserver.model;

/**
 * @author Hexrt
 * @description Osgb模型
 * @create 2022-03-11 22:40
 */
public class Osgb {
    /**
     * 模型编号
     */
    private Long id;
    /**
     * 资源地址
     */
    private String url;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 数据状态
     */
    private Integer isDeleted;
    /**
     * 创建时间
     */
    private Long gmtCreate;
    /**
     * 最近修改时间
     */
    private Long gmtModify;

    public Osgb() {
    }

    public Osgb(Long id, String url, String name, Integer isDeleted, Long gmtCreate, Long gmtModify) {
        this.id = id;
        this.url = url;
        this.name = name;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                '}';
    }
}
