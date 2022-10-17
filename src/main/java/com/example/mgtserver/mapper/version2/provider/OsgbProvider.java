package com.example.mgtserver.mapper.version2.provider;

import com.example.mgtserver.model.KmlLayer;
import com.example.mgtserver.model.version2.Osgb;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Hexrt
 * @description
 * @create 2022-04-09 15:40
 */
public class OsgbProvider {
    private final String TABLE_NAME = "osgb_v2";

    /**
     * 插入一条数据
     * @param osgb 模型数据
     * @return sql
     */
    public String insert(Osgb osgb) {
        return new SQL(){{
            INSERT_INTO(TABLE_NAME);
            VALUES("project_id", "#{projectId}");
            VALUES("url", "#{url}");
            VALUES("name", "#{name}");
            VALUES("description", "#{description}");
            VALUES("is_checked", "#{isChecked}");
            VALUES("is_deleted", "#{isDeleted}");
            VALUES("gmt_create", "#{gmtCreate}");
            VALUES("gmt_modify", "#{gmtModify}");
        }}.toString();
    }

    /**
     * 更新人工模型数据
     * @param osgb 模型数据
     * @return sql
     */
    public String update(Osgb osgb) {
        return new SQL(){{
            UPDATE(TABLE_NAME);
            if (null != osgb.getUrl()) {
                SET("url = #{url}");
            }
            if (null != osgb.getName()) {
                SET("name = #{name}");
            }
            if (null != osgb.getIsChecked()) {
                SET("is_checked = #{isChecked}");
            }
            if (null != osgb.getIsDeleted()) {
                SET("is_deleted = #{isDeleted}");
            }
            if (null != osgb.getDescription()) {
                SET("description = #{description}");
            }
            if (null != osgb.getGmtModify()) {
                SET("gmt_modify = #{gmtModify}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }

    /**
     * 查询模型数据
     * @param osgb 模型数据
     * @return sql
     */
    public String query(Osgb osgb) {
        return new SQL(){{
            SELECT("*");
            FROM(TABLE_NAME);
            if (null != osgb.getId()) {
                WHERE("id = #{id}");
            }
            if (null != osgb.getProjectId()) {
                WHERE("project_id = #{projectId}");
            }
            if (null != osgb.getName()) {
                WHERE("name LIKE CONCAT('%', CONCAT(#{name}, '%'))");
            }
            if (null != osgb.getUrl()) {
                WHERE("url LIKE CONCAT('%', CONCAT(#{url}, '%'))");
            }
            if (null != osgb.getDescription()) {
                WHERE("description = #{description}");
            }
            if (null != osgb.getIsDeleted()) {
                WHERE("is_deleted = #{isDeleted}");
            }
            if (null != osgb.getIsChecked()) {
                WHERE("is_checked = #{isChecked}");
            }
            // 保留对时间的查询条件
            // 默认按修改时间排序
            ORDER_BY("gmt_modify DESC");
        }}.toString();
    }

    /**
     * 通过模型id查询模型数据
     * @param id 模型id
     * @return sql
     */
    public String queryById(Long id) {
        return new SQL(){{
            SELECT("*");
            FROM(TABLE_NAME);
            WHERE("id = #{id}");
            LIMIT(1);
        }}.toString();
    }
}
