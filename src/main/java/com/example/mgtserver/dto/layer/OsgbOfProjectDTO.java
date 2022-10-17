package com.example.mgtserver.dto.layer;

/**
 * @author Hexrt
 * @create 2022年4月3日 21点28分
 */
public class OsgbOfProjectDTO {
    private Long id;
    private Long osgbId;
    private Long projectId;
    private String url;
    private String name;
    private Integer isChecked;
    private Integer isDeleted;
    private Long gmtCreate;
    private Long gmtModify;

    public OsgbOfProjectDTO() {
    }

    /**
     * osgb逻辑形式
     * @param id        osgbOfProject的id
     * @param osgbId
     * @param projectId
     * @param url
     * @param name
     * @param isChecked 是否默认勾选
     * @param isDeleted 是否删除
     * @param gmtCreate 创建时间
     * @param gmtModify 修改时间
     */
    public OsgbOfProjectDTO(Long id, Long osgbId, Long projectId, String url, String name, Integer isChecked, Integer isDeleted, Long gmtCreate, Long gmtModify) {
        this.id = id;
        this.osgbId = osgbId;
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

    public Long getOsgbId() {
        return osgbId;
    }

    public void setOsgbId(Long osgbId) {
        this.osgbId = osgbId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
        return "OsgbOfProjectDTO{" +
                "id=" + id +
                ", osgbId=" + osgbId +
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
