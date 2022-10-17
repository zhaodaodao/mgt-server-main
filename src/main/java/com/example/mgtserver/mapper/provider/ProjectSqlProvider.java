package com.example.mgtserver.mapper.provider;

import com.example.mgtserver.model.Project;
import org.apache.ibatis.jdbc.SQL;

public class ProjectSqlProvider {
    public String addProject(Project project) {
        return new SQL() {
            {
                INSERT_INTO("project");
                if (project.getName() != null) {
                    VALUES("name", "#{name}");
                }
                if (project.getGmtCreate() != null) {
                    VALUES("gmt_create", "#{gmtCreate}");
                }
                if (project.getGmtModify() != null) {
                    VALUES("gmt_modify", "#{gmtModify}");
                }
                if (project.getLongitude() != null) {
                    VALUES("longitude", "#{longitude}");
                }
                if (project.getLatitude() != null) {
                    VALUES("latitude", "#{latitude}");
                }
                if (project.getImgUrl() != null) {
                    VALUES("img_url", "#{imgUrl}");
                }
                if (project.getGmtStart() != null) {
                    VALUES("gmt_start", "#{gmtStart}");
                }
                if (project.getGmtEnd() != null) {
                    VALUES("gmt_end", "#{gmtEnd}");
                }
                if (project.getIntroduction() != null) {
                    VALUES("introduction", "#{introduction}");
                }
            }
        }.toString();
    }
}