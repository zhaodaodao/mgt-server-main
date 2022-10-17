package com.example.mgtserver.dto;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-06 15:41
 */
public class UserLoginDTO {
    private String username;
    private String password;
    private String verifyCode;

    public UserLoginDTO() {
    }

    /**
     * 登录dto
     * @param username   用户名
     * @param password   密码
     * @param verifyCode 验证码
     */
    public UserLoginDTO(String username, String password, String verifyCode) {
        this.username = username;
        this.password = password;
        this.verifyCode = verifyCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                '}';
    }
}
