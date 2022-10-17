package com.example.mgtserver.mapper.provider;

import com.example.mgtserver.dto.layer.OsgbOfProjectDTO;
import com.example.mgtserver.model.Osgb;
import com.example.mgtserver.model.OsgbOfProject;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Hexrt
 * @description
 * @create 2022-04-03 21:43
 */
public class OsgbProvider {
    private final String OSGB = "osgb";
    private final String OSGB_OF_PROJECT = "osgb_of_project";
    private final String NOT_DELETED = "is_deleted = 0";

    /**
     * 更新osgb模型信息
     * @param osgb 模型信息
     * @return sql
     */
    public String update(Osgb osgb) {
        return new SQL(){{
            UPDATE(OSGB);
            if (null != osgb.getName()) {
                SET("name = #{name}");
            }
            WHERE("id = #{id}");
            WHERE(NOT_DELETED);
        }}.toString();
    }

    /**
     * 查询osgb模型信息
     * @param osgb 模型信息
     * @return sql
     */
    public String fetch(Osgb osgb) {
        return new SQL(){{
            SELECT("*");
            FROM(OSGB);
            if (null != osgb.getId()) {
                WHERE("id = #{id}");
            }
            if (null != osgb.getName()) {
                WHERE("name LIKE CONCAT('%', #{name}, '%')");
            }
            if (null != osgb.getUrl()) {
                WHERE("url LIKE CONCAT('%', #{url}, '%')");
            }
            WHERE(NOT_DELETED);
        }}.toString();
    }

    /**
     * 删除模型与项目的关系
     * @param osgbOfProjectDTO 模型关系
     * @return sql
     */
    public String removeFromProject(OsgbOfProjectDTO osgbOfProjectDTO) {
        return new SQL(){{
            UPDATE(OSGB_OF_PROJECT);
            SET("is_deleted = 1");
            if (null != osgbOfProjectDTO.getGmtModify()) {
                SET("gmt_modify = #{gmtModify}");
            }
            WHERE(NOT_DELETED);
            if (null != osgbOfProjectDTO.getId()) {
                WHERE("id = #{id}");
            }
            if (null != osgbOfProjectDTO.getProjectId()) {
                WHERE("project_id = #{projectId}");
            }
            if (null != osgbOfProjectDTO.getOsgbId()) {
                WHERE("osgb_id = #{osgbId}");
            }
        }}.toString();
    }

    /**
     * 获取模型与项目关系模型
     * @param dto 关系模型
     * @return sql
     */
    public String fetchFromProject(OsgbOfProjectDTO dto) {
        return new SQL(){{
            SELECT("B.id as id");
            SELECT("osgb_id as osgb_id");
            SELECT("project_id");
            SELECT("url");
            SELECT("name");
            SELECT("B.is_deleted as is_deleted");
            SELECT("B.is_checked as is_checked");
            SELECT("B.gmt_create as gmt_create");
            SELECT("B.gmt_modify as gmt_modify");
            FROM("osgb AS A");
            FROM("osgb_of_project AS B");
            WHERE("A.id = B.osgb_id");
            WHERE("B.is_deleted = 0");
            WHERE("B.project_id = #{projectId}");
            if (null != dto.getName()) {
                WHERE("A.name LIKE CONCAT('%', #{name}, '%')");
            }
            if (null != dto.getUrl()) {
                WHERE("A.url LIKE CONCAT('%', #{url}, '%')");
            }
            if (null != dto.getOsgbId()) {
                WHERE("A.id = #{osgbId}");
            }
            if (null != dto.getProjectId()) {
                WHERE("B.project_id = #{projectId}");
            }
            ORDER_BY("gmt_modify DESC");
        }}.toString();
    }

    /**
     * 更新项目信息
     * @param dto 项目信息
     * @return 项目地址
     */
    public String updateFromProject(OsgbOfProjectDTO dto) {
        return new SQL(){{
            UPDATE(OSGB_OF_PROJECT);
            if (null != dto.getIsChecked()) {
                SET("is_checked = #{isChecked}");
            }
            if (null != dto.getIsDeleted()) {
                SET("is_deleted = #{isDeleted}");
            }
            if (null != dto.getGmtModify()) {
                SET("gmt_modify = #{gmtModify}");
            }
            if (null != dto.getId()) {
                WHERE("id = #{id}");
            }
            if (null != dto.getOsgbId()) {
                WHERE("osgb_id = #{osgbId}");
            }
            if (null != dto.getProjectId()) {
                WHERE("project_id = #{projectId}");
            }
        }}.toString();
    }

    /**
     * 插入osgb至信息
     * @param osgbOfProject 组合信息对象
     * @return sql
     */
    public String insertToProject(OsgbOfProject osgbOfProject) {
        return new SQL(){
            {
                INSERT_INTO(OSGB_OF_PROJECT);
                if (osgbOfProject.getProjectId() != null) {
                    VALUES("project_id", "#{projectId}");
                }
                if (osgbOfProject.getOsgbId() != null) {
                    VALUES("osgb_id", "#{osgbId}");
                }
                if (osgbOfProject.getIsChecked() != null) {
                    VALUES("is_checked", "#{isChecked}");
                }
                if (osgbOfProject.getGmtCreate() != null) {
                    VALUES("gmt_create", "#{gmtCreate}");
                }
                if (osgbOfProject.getGmtModify() != null) {
                    VALUES("gmt_modify", "#{gmtModify}");
                }
            }
        }.toString();
    }
}
