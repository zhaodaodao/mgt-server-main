package com.example.mgtserver.dto;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-07 19:23
 */
public class UserLoginStateDTO {
    private String token;
    private String username;
    private Integer role;

    public UserLoginStateDTO() {}

    public UserLoginStateDTO(String token, String username, Integer role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserLoginStateDTO{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
