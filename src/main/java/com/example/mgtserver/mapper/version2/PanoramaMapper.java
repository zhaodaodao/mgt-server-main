package com.example.mgtserver.mapper.version2;

import com.example.mgtserver.mapper.version2.provider.PanoramaProvider;
import com.example.mgtserver.model.Panorama;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hexrt
 * @description
 * @create 2022-04-09 15:29
 */
@Repository("panoramaMapperV2")
@Mapper
public interface PanoramaMapper {
    /**
     * 插入一条模型信息
     * @param panorama 模型数据
     * @return 插入结果
     */
    @InsertProvider(type = PanoramaProvider.class, method = "insert")
    Integer insert(Panorama panorama);

    /**
     * 更新模型数据
     * @param panorama 模型数据
     * @return 更新结果
     */
    @UpdateProvider(type = PanoramaProvider.class, method = "update")
    Integer update(Panorama panorama);

    /**
     * 更新条件查询模型
     * @param panorama 条件模型数据
     * @return 模型列表
     */
    @SelectProvider(type = PanoramaProvider.class, method = "query")
    List<Panorama> query(Panorama panorama);

    /**
     * 通过模型id查询模型数据
     * @param id 模型id
     * @return 模型数据
     */
    @SelectProvider(type = PanoramaProvider.class, method = "queryById")
    Panorama queryById(Long id);
}
