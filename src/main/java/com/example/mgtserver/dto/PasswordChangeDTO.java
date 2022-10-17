package com.example.mgtserver.dto;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-06 18:02
 */
public class PasswordChangeDTO {
    private String token;
    private String oldPassword;
    private String newPassword;

    public PasswordChangeDTO() {
    }

    public PasswordChangeDTO(String token, String oldPassword, String newPassword) {
        this.token = token;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "PasswordChangeDTO{" +
                "token='" + token + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
