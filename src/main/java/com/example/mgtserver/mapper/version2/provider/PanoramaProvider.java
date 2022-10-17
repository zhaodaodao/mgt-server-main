package com.example.mgtserver.mapper.version2.provider;

import com.example.mgtserver.model.Panorama;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Hexrt
 * @description
 * @create 2022-04-09 15:41
 */
public class PanoramaProvider {
    private final String TABLE_NAME = "panorama";

    /**
     * 插入一条数据
     * @param panorama 模型数据
     * @return sql
     */
    public String insert(Panorama panorama) {
        return new SQL(){{
            INSERT_INTO(TABLE_NAME);
            VALUES("project_id", "#{projectId}");
            VALUES("url", "#{url}");
            VALUES("name", "#{name}");
            VALUES("longitude","#{longitude}");
            VALUES("latitude","#{latitude}");
            VALUES("altitude","#{altitude}");
            VALUES("description","#{description}");
            VALUES("is_checked", "#{isChecked}");
            VALUES("is_deleted", "#{isDeleted}");
            VALUES("gmt_create", "#{gmtCreate}");
            VALUES("gmt_modify", "#{gmtModify}");
        }}.toString();
    }

    /**
     * 更新人工模型数据
     * @param panorama 模型数据
     * @return sql
     */
    public String update(Panorama panorama) {
        return new SQL(){{
            UPDATE(TABLE_NAME);
            if (null != panorama.getUrl()) {
                SET("url = #{url}");
            }
            if (null != panorama.getName()) {
                SET("name = #{name}");
            }
            if (null != panorama.getDescription()) {
                SET("description = #{description}");
            }
            if (null != panorama.getLongitude()) {
                SET("longitude = #{longitude}");
            }
            if (null != panorama.getLatitude()) {
                SET("latitude = #{latitude}");
            }
            if (null != panorama.getAltitude()) {
                SET("altitude = #{altitude}");
            }
            if (null != panorama.getIsChecked()) {
                SET("is_checked = #{isChecked}");
            }
            if (null != panorama.getIsDeleted()) {
                SET("is_deleted = #{isDeleted}");
            }
            if (null != panorama.getGmtModify()) {
                SET("gmt_modify = #{gmtModify}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }

    /**
     * 查询模型数据
     * @param panorama 模型数据
     * @return sql
     */
    public String query(Panorama panorama) {
        return new SQL(){{
            SELECT("*");
            FROM(TABLE_NAME);
            if (null != panorama.getId()) {
                WHERE("id = #{id}");
            }
            if (null != panorama.getProjectId()) {
                WHERE("project_id = #{projectId}");
            }
            if (null != panorama.getName()) {
                WHERE("name LIKE CONCAT('%', CONCAT(#{name}, '%'))");
            }
            if (null != panorama.getUrl()) {
                WHERE("url LIKE CONCAT('%', CONCAT(#{url}, '%'))");
            }
            if (null != panorama.getDescription()) {
                WHERE("description LIKE CONCAT('%', CONCAT(#{description}, '%'))");
            }
            if (null != panorama.getAltitude()) {
                WHERE("altitude LIKE CONCAT('%', CONCAT(#{altitude}, '%'))");
            }
            if (null != panorama.getLatitude()) {
                WHERE("latitude LIKE CONCAT('%', CONCAT(#{latitude}, '%'))");
            }
            if (null != panorama.getLongitude()) {
                WHERE("longitude LIKE CONCAT('%', CONCAT(#{longitude}, '%'))");
            }
            if (null != panorama.getIsDeleted()) {
                WHERE("is_deleted = #{isDeleted}");
            }
            if (null != panorama.getIsChecked()) {
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
