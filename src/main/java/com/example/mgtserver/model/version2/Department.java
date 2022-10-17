package com.example.mgtserver.model.version2;


public class Department {

    private Long id;
    private String name;
    private String manager;
    private String address;
    private Integer userCount;

    public Department() {
    }

    public Department(Long id, String name, String manager, String address, Integer userCount) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.address = address;
        this.userCount = userCount;
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

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                ", address='" + address + '\'' +
                ", user_count=" + userCount +
                '}';
    }
}
