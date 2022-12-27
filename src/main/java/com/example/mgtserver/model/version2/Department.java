package com.example.mgtserver.model.version2;


public class Department {

    /**
     * 主键
     */
    private Long id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 部门经理
     */
    private String manager;
    /**
     * 部门地址
     */
    private String address;
    /**
     * 部门人数
     */
    private Integer userCount;
    /**
     * 是否禁用
     */
    private Integer isDisable;
    /**
     * 创建时间
     */
    private Long gmtCreate;
    /**
     * 最近修改时间
     */
    private Long gmtModify;

    public Department() {
    }

    public Department(Long id, String name, String manager, String address, Integer userCount, Integer isDisable, Long gmtCreate, Long gmtModify) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.address = address;
        this.userCount = userCount;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
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
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                ", address='" + address + '\'' +
                ", userCount=" + userCount +
                ", isDisable=" + isDisable +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                '}';
    }
}
