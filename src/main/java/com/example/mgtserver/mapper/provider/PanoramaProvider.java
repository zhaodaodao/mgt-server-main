package com.example.mgtserver.mapper.provider;

import com.example.mgtserver.model.Panorama;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.Assert;

/**
 * @author Hexrt
 * @description 全景图的拓展属性
 * @create 2022-04-03 12:24
 */
public class PanoramaProvider {
    /**
     * 表名
     */
    private static final String TABLE_NAME = "panorama";
    /**
     * 未被删除
     */
    private static final String NOT_DELETED = "is_deleted = 0";

    /**
     * 条件查询
     * @param panorama 全景图查询相关信息
     * @return 查询结果
     */
    public String fetch(Panorama panorama) {
        return new SQL() {{
            SELECT("*");
            FROM(TABLE_NAME);
            if (null != panorama.getProjectId()) {
                WHERE("project_id = #{projectId}");
            }
            if (null != panorama.getId()) {
                WHERE("id = #{id}");
            }
            if (null != panorama.getName()) {
                WHERE("name LIKE CONCAT('%', #{name}, '%')");
            }
            WHERE(NOT_DELETED);
            ORDER_BY("gmt_modify DESC");
        }}.toString();
    }

    /**
     * 更新
     * @param panorama 更新
     * @return 更新语句
     */
    public String update(Panorama panorama) {
        return new SQL(){{
            UPDATE(TABLE_NAME);
            if (null != panorama.getProjectId()) {
                SET("project_id = #{projectId}");
            }
            if (null != panorama.getUrl()) {
                SET("url = #{url}");
            }
            if (null != panorama.getName()) {
                SET("name = #{name}");
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
            Assert.notNull(panorama.getId(), "id为空");
            WHERE("id = #{id}");

            LIMIT(1);
        }}.toString();
    }
}
