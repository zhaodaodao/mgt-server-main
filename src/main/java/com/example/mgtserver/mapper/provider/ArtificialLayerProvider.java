package com.example.mgtserver.mapper.provider;

import com.example.mgtserver.model.ArtificialLayer;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.Assert;

/**
 * @author Hexrt
 * @create 2022-04-04 2:22
 */
public class ArtificialLayerProvider {
    private final String TABLE_NAME = "artificial_layer";
    private final String NOT_DELETED = "is_deleted = 0";

    public String fetch(ArtificialLayer layer) {
        return new SQL(){{
            SELECT("*");
            FROM(TABLE_NAME);
            if (null != layer.getId()) {
                WHERE("id = #{id}");
            }
            if (null != layer.getProjectId()) {
                WHERE("project_id = #{projectId}");
            }
            if (null != layer.getIsChecked()) {
                WHERE("is_checked = #{is_checked}");
            }
            if (null != layer.getName()) {
                WHERE("name LIKE CONCAT('%', #{name}, '%')");
            }
            if (null != layer.getUrl()) {
                WHERE("url LIKE CONCAT('%', #{url}, '%')");
            }
            WHERE(NOT_DELETED);
            ORDER_BY("gmt_modify DESC");
        }}.toString();
    }

    public String update(ArtificialLayer layer) {
        return new SQL(){{
            UPDATE(TABLE_NAME);
            if (null != layer.getProjectId()) {
                SET("project_id = #{projectId}");
            }
            if (null != layer.getUrl()) {
                SET("url = #{url}");
            }
            if (null != layer.getName()) {
                SET("name = #{name}");
            }
            if (null != layer.getIsChecked()) {
                SET("is_checked = #{isChecked}");
            }
            if (null != layer.getDescription()) {
                SET("description = #{description}");
            }
            if (null != layer.getIsDeleted()) {
                SET("is_deleted = #{isDeleted}");
            }
            if (null != layer.getGmtModify()) {
                SET("gmt_modify = #{gmtModify}");
            }
            Assert.notNull(layer.getId(), "id为空");
            WHERE("id = #{id}");
            LIMIT(1);
        }}.toString();
    }
}
