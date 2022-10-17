package com.example.mgtserver.mapper;


import com.example.mgtserver.mapper.provider.ArtificialLayerProvider;
import com.example.mgtserver.model.ArtificialLayer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hexrt
 */
@Mapper
@Repository("artificialLayer")
public interface ArtificialLayerMapper {
    /**
     * @param projectId      人工模型所属项目的id
     * @param url            人工模型资源文件的url地址
     * @param name           人工模型资源的名字
     * @param isChecked      人工模型上次勾选状态
     * @param currentTime    人工模型上传时间(当前时间)
     * @return 影响行数
     */
    @Insert("INSERT INTO artificial_layer (project_id, url, name,is_checked,description, gmt_create, gmt_modify) " +
            "VALUES (#{projectId}, #{url}, #{name}, #{isChecked},#{description}, #{currentTime}, #{currentTime})")
    Integer insert(Long projectId, String url, String name, Integer isChecked,String description, Long currentTime);

    /**
     * 获取所有人工模型信息
     *
     * @return 所有人工模型文件信息
     */
    @Select("SELECT * FROM artificial_layer WHERE is_deleted = 0 ORDER BY gmt_modify DESC")
    List<ArtificialLayer> fetchAll();

    /**
     * 根据id删除人工模型数据
     *
     * @param id 人工模型文件id
     * @return 影响行数
     */
    @Update("UPDATE artificial_layer SET is_deleted = 1, gmt_modify = #{currentTime} WHERE id = #{id} AND is_deleted = 0")
    Integer delete(Long id, Long currentTime);

    /**
     * 更新人工模型数据
     *
     * @param artificialLayer
     * @return
     */
    @UpdateProvider(type = ArtificialLayerProvider.class, method = "update")
    Integer update(ArtificialLayer artificialLayer);

    /**
     * 查询人工模型
     * @param layer 查询条件
     * @return
     */
    @SelectProvider(type = ArtificialLayerProvider.class, method = "fetch")
    List<ArtificialLayer> fetch(ArtificialLayer layer);

    /**
     * 从项目中获取所有的人工模型信息
     * @param projectId 项目id
     * @return
     */
    @Select("SELECT * FROM artificial_layer WHERE project_id = #{projectId} AND is_deleted = 0 ORDER BY gmt_modify DESC")
    List<ArtificialLayer> fetchByProjectId(Long projectId);
}
