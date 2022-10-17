package com.example.mgtserver.mapper.version2.provider;

import com.example.mgtserver.model.version2.UserLoginState;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-06 11:45
 */
public class UserLoginStateProvider {
    private static final String TABLE_NAME = "user_login_state";

    public String insert(UserLoginState loginState) {
        return new SQL(){{
            INSERT_INTO(TABLE_NAME);
            VALUES("token", "#{token}");
            VALUES("user_id", "#{userId}");
            VALUES("expire", "#{expire}");
        }}.toString();
    }

    public String update(UserLoginState loginState) {
        return new SQL(){{
            UPDATE(TABLE_NAME);
            if (loginState.getExpire() != null) {
                SET("expire = #{expire}");
            }
            if (loginState.getUserId() != null) {
                SET("userId = #{userId}");
            }
        }}.toString();
    }

    public String queryByUserId(Long userId) {
        return new SQL(){{
            SELECT("*");
            FROM(TABLE_NAME);
            WHERE("user_id = #{userId}");
        }}.toString();
    }

    public String queryByToken(String token) {
        return new SQL(){{
            SELECT("*");
            FROM(TABLE_NAME);
            WHERE("token = #{token}");
        }}.toString();
    }

    public String deleteByToken(String token) {
        return new SQL(){{
            DELETE_FROM(TABLE_NAME);
            WHERE("token = #{token}");
        }}.toString();
    }

    public String deleteByUserId(Long userId) {
        return new SQL(){{
            DELETE_FROM(TABLE_NAME);
            WHERE("user_id = #{userId}");
        }}.toString();
    }
}
