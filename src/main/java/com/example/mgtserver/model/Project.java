package com.example.mgtserver.model;

/**
 * @author Hexrt
 * @description 项目
 * @create 2022-03-11 22:45
 */
public class Project {
    /**
     * 项目id
     */
    private Long id;
    /**
     * 项目名称
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
    /**
     * 摄像机经度
     */
    private String longitude;
    /**
     * 摄像机纬度
     */
    private String latitude;

    /**
     * 摄像机高程
     */
    private String altitude;

    /**
     * 项目图片路径
     */
    private String imgUrl;

    /**
     * 项目开始时间
     */
    private Long gmtStart;

    /**
     * 项目预计结束时间
     */
    private Long gmtEnd;

    /**
     * 项目简介
     */
    private String introduction;

    public Project() {
    }

    public Project(Long id, String name, Integer isDeleted, Long gmtCreate, Long gmtModify, String longitude, String latitude, String altitude, String imgUrl, Long gmtStart, Long gmtEnd, String introduction) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.imgUrl = imgUrl;
        this.gmtStart = gmtStart;
        this.gmtEnd = gmtEnd;
        this.introduction = introduction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Long gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Long getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Long gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String
    toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", altitude='" + altitude + '\'' +
                '}';
    }
}
