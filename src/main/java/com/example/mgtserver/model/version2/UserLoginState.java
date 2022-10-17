package com.example.mgtserver.model.version2;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-06 11:31
 */
public class UserLoginState {
    private String token;
    private Long userId;
    private Long expire;

    public UserLoginState() {
    }

    /**
     * 用户登录状态
     * @param token        状态token
     * @param userId       用户id
     * @param expire       过期时间精确到毫秒
     */
    public UserLoginState(String token, Long userId, Long expire) {
        this.token = token;
        this.userId = userId;
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return "UserLoginState{" +
                "token='" + token + '\'' +
                ", userId=" + userId +
                ", expire=" + expire +
                '}';
    }
}
