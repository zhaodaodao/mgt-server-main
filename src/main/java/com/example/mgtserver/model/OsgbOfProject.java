package com.example.mgtserver.model;

/**
 * @author Hexrt
 * @description area所属osgb关系模型
 * @create 2022-03-11 22:49
 */
public class OsgbOfProject {
    /**
     * 关系id
     */
    private Long id;
    /**
     * 区域模型id
     */
    private Long projectId;
    /**
     * osgb模型id
     */
    private Long osgbId;
    /**
     * 数据勾选展示状态
     */
    private Integer isChecked;
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

    public OsgbOfProject() {
    }

    public OsgbOfProject(Long id, Long projectId, Long osgbId, Integer isChecked, Integer isDeleted, Long gmtCreate, Long gmtModify) {
        this.id = id;
        this.projectId = projectId;
        this.osgbId = osgbId;
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

    public Long getOsgbId() {
        return osgbId;
    }

    public void setOsgbId(Long osgbId) {
        this.osgbId = osgbId;
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
        return "OsgbOfArea{" +
                "id=" + id +
                ", areaId=" + projectId +
                ", osgbId=" + osgbId +
                ", isChecked=" + isChecked +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                '}';
    }
}
