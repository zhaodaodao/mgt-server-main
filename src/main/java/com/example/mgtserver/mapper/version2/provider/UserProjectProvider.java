package com.example.mgtserver.mapper.version2.provider;

import com.example.mgtserver.model.version2.UserProject;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class UserProjectProvider {

    private static final String TABLE_NAME = "user_project";

    public String insert(UserProject userProject) {
        return new SQL(){{
            INSERT_INTO(TABLE_NAME);
            VALUES("pid", "#{pid}");
            VALUES("uid", "#{uid}");
            VALUES("is_disable", "#{isDisable}");
            VALUES("gmt_create", "#{gmtCreate}");
            VALUES("gmt_modify", "#{gmtModify}");
        }}.toString();
    }

    public String queryListByPid(Long pid) {
        return new SQL(){{
            SELECT("*");
            FROM(TABLE_NAME);
            WHERE("pid = #{pid} AND is_disable = 0");
        }}.toString();
    }

    public String query(UserProject userProject) {
        return new SQL(){{
            SELECT("*");
            FROM(TABLE_NAME);
            if (userProject.getId() != null) {
                WHERE("id = #{id}");
            }
            if (userProject.getUid() != null) {
                WHERE("pid = #{pid}");
            }
            if (userProject.getPid() != null) {
                WHERE("uid = #{uid}");
            }
            if (userProject.getIsDisable() != null) {
                WHERE("is_disable = #{isDisable}");
            }
        }}.toString();
    }

    public String update(UserProject userProject) {
        return new SQL(){{
            UPDATE(TABLE_NAME);
            if (userProject.getIsDisable() != null) {
                SET("is_disable = #{isDisable}");
            }
            if (userProject.getGmtModify() != null) {
                SET("gmt_modify = #{gmtModify}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }
}
