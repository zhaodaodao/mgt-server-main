package com.example.mgtserver.mapper.version2.provider;

import com.example.mgtserver.model.version2.Department;
import org.apache.ibatis.jdbc.SQL;

public class DepartmentProvider {

    private static final String TABLE_NAME = "t_department";

    public String insert(Department department) {
        return new SQL(){{
            INSERT_INTO(TABLE_NAME);
            VALUES("name", "#{name}");
            VALUES("manager", "#{manager}");
            VALUES("address", "#{address}");
            VALUES("user_count", "#{userCount}");
        }}.toString();
    }

    public String update(Department department) {
        return new SQL(){{
            UPDATE(TABLE_NAME);
            if (department.getName() != null) {
                SET("name = #{name}");
            }
            if (department.getManager() != null) {
                SET("manager = #{manager}");
            }
            if (department.getAddress() != null) {
                SET("address = #{address}");
            }
            if (department.getUserCount() != null) {
                SET("user_count = #{userCount}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }

    public String query(Department department) {
        return new SQL(){{
            SELECT("*");
            FROM(TABLE_NAME);
            if (department.getId() != null) {
                WHERE("id = #{id}");
            }
            if (department.getName() != null) {
                WHERE("name = #{name}");
            }
            if (department.getManager() != null) {
                WHERE("manager = #{manager}");
            }
            if (department.getAddress() != null) {
                WHERE("address = #{address}");
            }
            if (department.getUserCount() != null) {
                WHERE("user_count = #{userCount}");
            }
        }}.toString();
    }

    public String delete(Long id) {
        return new SQL(){{
          DELETE_FROM(TABLE_NAME);
          WHERE("id = #{id}");


        }}.toString();
    }
}
