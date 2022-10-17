package com.example.mgtserver.mapper;

import com.example.mgtserver.dto.project.ProjectStat;
import com.example.mgtserver.mapper.provider.ProjectSqlProvider;
import com.example.mgtserver.model.Project;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("projectMapper")
public interface ProjectMapper {
    /**
     * 插入一条项目信息
     *
     * @param project 待插入的项目对象
     * @return 影响行数
     */
    @InsertProvider(type = ProjectSqlProvider.class, method = "addProject")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(Project project);

    /**
     * 获取所有项目信息
     *
     * @return 所有项目
     */
    @Select("SELECT * FROM project WHERE is_deleted = 0")
    List<Project> fetchAll();

    /**
     * 更新项目信息
     *
     * @param name        项目名字
     * @param currentTime 现在时间
     * @return 修改状态值
     */
    @Update("UPDATE project SET name = #{name}, gmt_modify = #{currentTime} WHERE id = #{id} AND is_deleted = 0")
    Integer update(Long id, String name, Long currentTime);

    /**
     * 删除项目
     *
     * @param id 项目id
     * @return 删除结果
     */
    @Update("UPDATE project SET is_deleted = 1, gmt_modify = #{currentTime} WHERE id = #{id} AND is_deleted = 0 LIMIT 1")
    Integer delete(Long id, Long currentTime);

    /**
     * 查询项目统计
     * @param id 查询的项目的id
     * @return 项目统计信息对象
     */
    @Select("SELECT \n" +
            "       COUNT(DISTINCT oop.id) AS osgb_count,\n" +
            "       COUNT(DISTINCT kl.id) AS kml_layer_count,\n" +
            "       COUNT(DISTINCT al.id) AS artificial_layer_count,\n" +
            "       COUNT(DISTINCT pa.id) AS panorama_count\n" +
            "FROM project p\n" +
            "         LEFT JOIN osgb_v2 oop on p.id = oop.project_id AND oop.is_deleted = 0\n" +
            "         LEFT JOIN kml_layer kl on p.id = kl.project_id AND kl.is_deleted = 0\n" +
            "         LEFT JOIN artificial_layer al on p.id = al.project_id AND al.is_deleted = 0\n" +
            "         LEFT JOIN panorama pa on p.id = pa.project_id AND pa.is_deleted = 0\n" +
            "WHERE p.id = #{id}")
    @Result(column = "osgb_count",property = "osgbCount")
    @Result(column = "kml_layer_count",property = "kmlLayerCount")
    @Result(column = "artificial_layer_count",property = "artificialLayerCount")
    @Result(column = "panorama_count",property = "panoramaCount")
    ProjectStat queryProjectStatistic(Long id);
}
