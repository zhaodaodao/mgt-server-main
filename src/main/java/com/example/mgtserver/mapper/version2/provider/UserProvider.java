package com.example.mgtserver.mapper.version2.provider;

import com.example.mgtserver.model.version2.User;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-06 11:45
 */
public class UserProvider {
    private static final String TABLE_NAME = "user";

    public String insert(User user) {
        return new SQL(){{
            INSERT_INTO(TABLE_NAME);
            VALUES("username", "#{username}");
            VALUES("password", "#{password}");
            VALUES("role", "#{role}");
            VALUES("comment", "#{comment}");
            VALUES("is_disable", "#{isDisable}");
            VALUES("gmt_create", "#{gmtCreate}");
            VALUES("gmt_last_login", "#{gmtLastLogin}");
        }}.toString();
    }

    public String update(User user) {
        return new SQL(){{
            UPDATE(TABLE_NAME);
            if (user.getPassword() != null) {
                SET("password = #{password}");
            }
            if (user.getComment() != null) {
                SET("comment = #{comment}");
            }
            if (user.getGmtLastLogin() != null) {
                SET("gmt_last_login = #{gmtLastLogin}");
            }
            if (user.getRole() != null) {
                SET("role = #{role}");
            }
            if (user.getIsDisable() != null) {
                SET("is_disable = #{isDisable}");
            }
            if (user.getUsername() != null) {
                WHERE("username = #{username}");
            }
            if (user.getId() != null) {
                WHERE("id = #{id}");
            }
        }}.toString();
    }

    public String query(User user) {
        return new SQL(){{
            SELECT("*");
            FROM(TABLE_NAME);
            if (user.getId() != null) {
                WHERE("id = #{id}");
            }
            //todo 这里没有做模糊查询LIKE
            if (user.getUsername() != null) {
                WHERE("username = #{username}");
            }
            if (user.getRole() != null) {
                WHERE("role = ${role}");
            }
            if (user.getComment() != null) {
                WHERE("comment = #{comment}");
            }
            //todo 这里强制限制了不能查询密码
            if (user.getIsDisable() != null) {
                WHERE("is_disable = #{isDisable}");
            }
            if (user.getGmtCreate() != null) {
                WHERE("gmt_create = #{gmtCreate}");
            }
            if (user.getGmtLastLogin() != null) {
                WHERE("gmt_last_login = ${gmtLastLogin}");
            }
            ORDER_BY("role ASC, gmt_create DESC, gmt_last_login DESC");
        }}.toString();
    }

    public String queryById(Long id) {
        return new SQL(){{
            SELECT("*");
            FROM(TABLE_NAME);
            WHERE("id = #{id}");
        }}.toString();
    }

    public String queryByUsername(String username) {
        return new SQL(){{
            SELECT("*");
            FROM(TABLE_NAME);
            WHERE("username = #{username}");
        }}.toString();
    }

}
