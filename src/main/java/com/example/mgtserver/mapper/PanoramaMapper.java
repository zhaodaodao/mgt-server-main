package com.example.mgtserver.mapper;

import com.example.mgtserver.mapper.provider.PanoramaProvider;
import com.example.mgtserver.model.Panorama;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hexrt
 * @description 全景图模型
 * @create 2022-03-12 20:40
 */
@Mapper
@Repository("panoramaMapper")
public interface PanoramaMapper {
    /**
     * 插入一条全景图信息
     *
     * @param projectId   所属项目id
     * @param url         资源地址
     * @param name        资源名称
     * @param longitude   经度
     * @param latitude    纬度
     * @param altitude    高程
     * @param currentTime 当前时间
     * @return 插入结果
     */
    @Insert("INSERT INTO panorama (project_id, url, name, longitude, latitude, altitude, is_checked, gmt_create, gmt_modify) VALUES (#{projectId}, #{url}, #{name}, #{longitude}, #{latitude}, #{altitude}, #{isChecked}, #{currentTime}, #{currentTime})")
    Integer insert(long projectId, String url, String name, String longitude, String latitude, String altitude, int isChecked, long currentTime);

    /**
     * 删除全景图
     *
     * @param id          全景图id
     * @param currentTime 当前时间
     * @return 删除结果
     */
    @Update("UPDATE panorama SET is_deleted = 1, gmt_modify = #{currentTime} WHERE id = #{id} AND is_deleted = 0")
    Integer delete(long id, Long currentTime);


    /**
     * 更新
     * @param panorama     更新信息
     * @return 更新结果
     */
    @UpdateProvider(type = PanoramaProvider.class, method = "update")
    Integer update(Panorama panorama);

    /**
     * 查询某个区域的所有全景图模型
     *
     * @param projectId 项目id
     * @return 模型列表
     */
    @Select("SELECT * FROM panorama WHERE project_id = #{projectId} AND is_deleted = 0 ORDER BY gmt_modify DESC")
    List<Panorama> fetchByProjectId(long projectId);

    /**
     * 从未删除的资源查找对应id的资源
     *
     * @param id 资源id
     * @return 查询结果
     */
    @Select("SELECT * FROM panorama WHERE id = #{id} AND is_deleted = 0")
    Panorama fetchById(long id);

    /**
     * 获取全部未删除的全景图信息
     * @return 全景图列表
     */
    @Select("SELECT * FROM panorama WHERE is_deleted = 0 ORDER BY gmt_modify DESC")
    List<Panorama> fetchAll();

    /**
     * 条件查询
     * @param panorama 全景图相关信息
     * @return 查询结果
     */
    @SelectProvider(type = PanoramaProvider.class, method = "fetch")
    List<Panorama> fetch(Panorama panorama);


}
