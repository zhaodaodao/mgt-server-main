package com.example.mgtserver.model;

/**
 * @author Hexrt
 * @description 全景资源文件
 * @create 2022-03-11 22:42
 */
public class Panorama {
    /**
     * 全景资源编号
     */
    private Long id;
    /**
     * 所属项目id
     */
    private Long projectId;
    /**
     * 全景资源路径
     */
    private String url;
    /**
     * 全景资源名称
     */
    private String name;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 高程
     */
    private String altitude;
    /**
     * 数据勾选展示状态
     */
    private Integer isChecked;

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

    public Panorama() {
    }

    public Panorama(Long id, Long projectId, String url, String name, String longitude, String latitude, String altitude, Integer isChecked, String description, Integer isDeleted, Long gmtCreate, Long gmtModify) {
        this.id = id;
        this.projectId = projectId;
        this.url = url;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.isChecked = isChecked;
        this.description = description;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
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
        return "Panorama{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", altitude='" + altitude + '\'' +
                ", isChecked=" + isChecked +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                '}';
    }
}
