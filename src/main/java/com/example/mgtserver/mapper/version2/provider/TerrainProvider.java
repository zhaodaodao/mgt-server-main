package com.example.mgtserver.mapper.version2.provider;

import com.example.mgtserver.model.version2.Terrain;
import org.apache.ibatis.jdbc.SQL;

public class TerrainProvider {
    private final String TABLE_NAME = "terrain";

    private final String NOT_DELETED = "is_deleted = 0";

    /**
     * 插入一条数据
     * @param terrain 地形数据
     * @return sql
     */
    public String insert(Terrain terrain) {
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
     * @param terrain 人工模型数据
     * @return sql
     */
    public String update(Terrain terrain) {
        return new SQL(){{
            UPDATE(TABLE_NAME);
            if (null != terrain.getUrl()) {
                SET("url = #{url}");
            }
            if (null != terrain.getName()) {
                SET("name = #{name}");
            }
            if (null != terrain.getIsChecked()) {
                SET("is_checked = #{isChecked}");
            }
            if (null != terrain.getIsDeleted()) {
                SET("is_deleted = #{isDeleted}");
            }
            if (null != terrain.getDescription()) {
                SET("description = #{description}");
            }
            if (null != terrain.getGmtModify()) {
                SET("gmt_modify = #{gmtModify}");
            }
            if (null != terrain.getAssociatedOsgbId()) {
                SET("associated_osgb_id = #{associatedOsgbId}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }

    /**
     * 查询模型数据
     * @param terrain 模型数据
     * @return sql
     */
    public String query(Terrain terrain) {
        return new SQL(){{
            SELECT("*");
            FROM(TABLE_NAME);
            if (null != terrain.getId()) {
                WHERE("id = #{id}");
            }
            if (null != terrain.getProjectId()) {
                WHERE("project_id = #{projectId}");
            }
            if (null != terrain.getName()) {
                WHERE("name LIKE CONCAT('%', CONCAT(#{name}, '%'))");
            }
            if (null != terrain.getUrl()) {
                WHERE("url LIKE CONCAT('%', CONCAT(#{url}, '%'))");
            }
            if (null != terrain.getDescription()) {
                WHERE("description LIKE CONCAT('%', CONCAT(#{description}, '%'))");
            }
            if (null != terrain.getIsDeleted()) {
                WHERE("is_deleted = #{isDeleted}");
            }
            if (null != terrain.getIsChecked()) {
                WHERE("is_checked = #{isChecked}");
            }
            if (null != terrain.getAssociatedOsgbId()) {
                WHERE("associated_osgb_id = #{associatedOsgbId}");
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

    /**
     * 更新关联字段
     * @param terrain 目标行
     * @return sql
     */
    public String updateAssociatedOsgbId(Terrain terrain) {
        return new SQL(){{
            UPDATE(TABLE_NAME);
            if (null != terrain.getGmtModify()) {
                SET("gmt_modify = #{gmtModify}");
            }
            if (null != terrain.getAssociatedOsgbId()) {
                SET("associated_osgb_id = #{associatedOsgbId}");
            } else {
                SET("associated_osgb_id = NULL");
            }
            WHERE("id = #{id}");
            WHERE(NOT_DELETED);
        }}.toString();
    }
}
