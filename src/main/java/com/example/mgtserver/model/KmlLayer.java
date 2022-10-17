package com.example.mgtserver.model;

/**
 * @author Hexrt
 * @description KML
 * @create 2022-03-11 22:37
 */
public class KmlLayer {
    /**
     * KML资源id
     */
    private Long id;
    /**
     * 所属项目id
     */
    private Long projectId;

    /**
     * 资源路径
     */
    private String url;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 数据勾选展示状态
     */
    private Integer isChecked;
    /**
     * 文件描述
     */
    private String description;
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

    public KmlLayer() {
    }

    public KmlLayer(Long id, Long projectId, String url, String name, Integer isChecked, Integer isDeleted, Long gmtCreate, Long gmtModify) {
        this.id = id;
        this.projectId = projectId;
        this.url = url;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "KmlLayer{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", isChecked=" + isChecked +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                '}';
    }
}
