package com.example.mgtserver.mapper;

import com.example.mgtserver.dto.layer.OsgbOfProjectDTO;
import com.example.mgtserver.mapper.provider.OsgbProvider;
import com.example.mgtserver.model.Osgb;
import com.example.mgtserver.model.OsgbOfProject;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hexrt
 * @description
 * @create 2022-03-12 20:07
 */
@Mapper
@Repository("osgbMapper")
public interface OsgbMapper {
    /**
     * 插入一条osgb模型数据
     *
     * @param url         模型资源地址
     * @param name        模型名称
     * @param currentTime 当前时间
     * @return 插入数据的影响行数
     */
    @Insert("INSERT INTO osgb (url, name, gmt_create, gmt_modify) VALUES(#{url}, #{name}, #{currentTime}, #{currentTime})")
    Integer insert(String url, String name, Long currentTime);

    /**
     * 通过id查询模型
     *
     * @param id osgb模型编号
     * @return 模型信息
     */
    @Select("SELECT * FROM osgb WHERE id = #{id}")
    Osgb fetchById(Long id);

    /**
     * 通过url获取osgb模型
     * @param url osgb模型url
     * @return
     */
    @Select("SELECT * FROM osgb WHERE url = #{url} LIMIT 1")
    Osgb fetchByUrl(String url);

    /**
     * 删除模型
     *
     * @param id 模型id
     * @return 删除结果
     */
    @Update("UPDATE osgb SET is_deleted = 1 WHERE id = #{id}")
    Integer delete(Long id);

    /**
     * 更新osgb信息
     * @param osgb osgb
     * @return 更新结果
     */
    @UpdateProvider(type = OsgbProvider.class, method = "update")
    Integer update(Osgb osgb);

    /**
     * 条件查询
     * @param osgb osgb信息
     * @return 查询结果
     */
    @SelectProvider(type = OsgbProvider.class, method = "fetch")
    List<Osgb> fetch(Osgb osgb);

    /**
     * 插入一条区域选择osgb模型信息
     *
     * @param osgbOfProject 数据对象
     * @return 插入结果
     */
    @InsertProvider(type = OsgbProvider.class, method = "insertToProject")
    Integer insertToProject(OsgbOfProject osgbOfProject);

    /**
     * 删除osgb关系模型
     * @param osgbOfProjectDTO 关系模型
     * @return 删除结果
     */
    @UpdateProvider(type = OsgbProvider.class, method = "removeFromProject")
    Integer removeFromProject(OsgbOfProjectDTO osgbOfProjectDTO);


    /**
     * 获取所有模型信息
     * @deprecated 历史遗留接口
     * @return 模型列表
     */
    @Deprecated
    @Select("SELECT * FROM osgb WHERE is_deleted = 0")
    List<Osgb> fetchAll();

    /**
     * 更新模型信息
     * @param osgbOfProjectDTO osgb关系模型信息
     * @return
     */
    @UpdateProvider(type = OsgbProvider.class, method = "updateFromProject")
    Integer updateFromProject(OsgbOfProjectDTO osgbOfProjectDTO);

    /**
     * 条件查询
     * @param osgbOfProjectDTO 查询信息
     * @return 条件查询结果
     */
    @SelectProvider(type = OsgbProvider.class, method = "fetchFromProject")
    List<OsgbOfProjectDTO> fetchFromProject(OsgbOfProjectDTO osgbOfProjectDTO);

    /**
     * 通过双关键字查询是否存在此关系信息
     * @param projectId 项目id
     * @param osgbId    模型id
     * @return
     */
    @Select("SELECT * FROM osgb_of_project WHERE project_id = #{projectId} AND osgb_id = #{osgbId} AND is_deleted = 0")
    OsgbOfProject fetchByOsgbProject(Long projectId, Long osgbId);

    /**
     * 通过projectId来获取osgb模型
     * @param projectId 项目id
     * @return
     */
    @Select("SELECT B.id as id, osgb_id as osgb_id, project_id, url, name, B.is_deleted as is_deleted, B.is_checked as is_checked, B.gmt_create as gmt_create, B.gmt_modify as gmt_modify FROM osgb AS A, osgb_of_project AS B WHERE (A.id = B.osgb_id AND B.project_id = #{projectId} AND B.is_deleted = 0) ORDER BY gmt_modify DESC")
    List<OsgbOfProjectDTO> fetchByProjectId(Long projectId);
}
