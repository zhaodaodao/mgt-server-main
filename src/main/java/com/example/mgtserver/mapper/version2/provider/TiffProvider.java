package com.example.mgtserver.mapper.version2.provider;

import com.example.mgtserver.model.version2.Tiff;
import org.apache.ibatis.jdbc.SQL;

public class TiffProvider {
    private final String TABLE_NAME = "tiff";

    /**
     * 插入一条数据
     * @param tiff 卫星影像图数据模型
     * @return sql
     */
    public String insert(Tiff tiff) {
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
            VALUES("start", "#{start}");
            VALUES("end", "#{end}");
        }}.toString();
    }

    /**
     * 更新人工模型数据
     * @param tiff 人工模型数据
     * @return sql
     */
    public String update(Tiff tiff) {
        return new SQL(){{
            UPDATE(TABLE_NAME);
            if (null != tiff.getUrl()) {
                SET("url = #{url}");
            }
            if (null != tiff.getName()) {
                SET("name = #{name}");
            }
            if (null != tiff.getIsChecked()) {
                SET("is_checked = #{isChecked}");
            }
            if (null != tiff.getIsDeleted()) {
                SET("is_deleted = #{isDeleted}");
            }
            if (null != tiff.getDescription()) {
                SET("description = #{description}");
            }
            if (null != tiff.getGmtModify()) {
                SET("gmt_modify = #{gmtModify}");
            }
            if (null != tiff.getStart()) {
                SET("start = #{start}");
            }
            if (null != tiff.getEnd()) {
                SET("end = #{end}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }

    /**
     * 查询卫星影像图
     * @param tiff 卫星影像图数据模型
     * @return sql
     */
    public String query(Tiff tiff) {
        return new SQL(){{
            SELECT("*");
            FROM(TABLE_NAME);
            if (null != tiff.getId()) {
                WHERE("id = #{id}");
            }
            if (null != tiff.getProjectId()) {
                WHERE("project_id = #{projectId}");
            }
            if (null != tiff.getName()) {
                WHERE("name LIKE CONCAT('%', CONCAT(#{name}, '%'))");
            }
            if (null != tiff.getUrl()) {
                WHERE("url LIKE CONCAT('%', CONCAT(#{url}, '%'))");
            }
            if (null != tiff.getDescription()) {
                WHERE("description LIKE CONCAT('%', CONCAT(#{description}, '%'))");
            }
            if (null != tiff.getIsDeleted()) {
                WHERE("is_deleted = #{isDeleted}");
            }
            if (null != tiff.getIsChecked()) {
                WHERE("is_checked = #{isChecked}");
            }
            // 保留对时间的查询条件
            // 默认按修改时间排序
            ORDER_BY("gmt_modify DESC");
        }}.toString();
    }

    /**
     * 通过影像图id查询
     * @param id 影像图id
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
