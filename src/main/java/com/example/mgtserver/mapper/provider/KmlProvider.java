package com.example.mgtserver.mapper.provider;

import com.example.mgtserver.model.KmlLayer;
import org.apache.ibatis.jdbc.SQL;

public class KmlProvider {

    public String updateKml(KmlLayer kmlLayer) {
        return new SQL() {
            {
                UPDATE("kml_layer");
                if (kmlLayer.getName() != null) {
                    SET("name=#{name}");
                }
                if (kmlLayer.getUrl() != null) {
                    SET("url=#{url}");
                }
                if (kmlLayer.getIsChecked() != null) {
                    SET("is_checked=#{isChecked}");
                }
                if (kmlLayer.getDescription() != null) {
                    SET("description=#{description}");
                }
                if (kmlLayer.getGmtModify() != null) {
                    SET("gmt_modify=#{gmtModify}");
                }
                WHERE("id=#{id}");
            }
        }.toString();
    }

    public String fetch(KmlLayer kmlLayer) {
        return new SQL() {
            {
                SELECT("*");
                FROM("kml_layer");
                if (null != kmlLayer.getProjectId()) {
                    WHERE("project_id = #{projectId}");
                }
                if (null != kmlLayer.getId()) {
                    WHERE("id = #{id}");
                }
                if (null != kmlLayer.getName()) {
                    WHERE("name LIKE CONCAT('%', #{name}, '%')");
                }
                WHERE("is_deleted = 0");
                ORDER_BY("gmt_modify DESC");
            }
        }.toString();
    }
}
