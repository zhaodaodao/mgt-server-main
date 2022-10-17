package com.example.mgtserver.model.version2;

/**
 * 卫星影像图数据模型
 */
public class Tiff {
    /**
     * 主键
     */
    private Long id;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 名称
     */
    private String name;
    /**
     * 逻辑删除字段
     */
    private Integer isDeleted;
    /**
     * 是否默认加载
     */
    private Integer isChecked;
    /**
     * 创建时间
     */
    private Long gmtCreate;
    /**
     * 修改时间
     */
    private Long gmtModify;
    /**
     * 描述
     */
    private String description;
    /**
     * 资源文件夹url
     */
    private String url;
    /**
     * 资源文件名开始数字
     */
    private Integer start;
    /**
     * 资源文件名结束数字
     */
    private Integer end;

    public Tiff() {
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
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

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Tiff{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                ", isChecked=" + isChecked +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
