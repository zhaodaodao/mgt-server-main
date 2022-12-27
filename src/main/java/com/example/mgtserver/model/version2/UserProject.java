package com.example.mgtserver.model.version2;

/**
 * @author mtyu007
 * @description 用户项目关联表
 * @create 2022-12-10 17:15
 */
public class UserProject {
    /**
     * 主键
     */
    private Long id;
    /**
     * 项目id
     */
    private Long pid;
    /**
     * 用户id
     */
    private Long uid;
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

    public UserProject(Long id, Long pid, Long uid, Integer isDisable, Long gmtCreate, Long gmtModify) {
        this.id = id;
        this.pid = pid;
        this.uid = uid;
        this.isDisable = isDisable;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
    }

    public UserProject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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
        return "UserProject{" +
                "id=" + id +
                ", pid=" + pid +
                ", uid=" + uid +
                ", isDisable=" + isDisable +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                '}';
    }
}
