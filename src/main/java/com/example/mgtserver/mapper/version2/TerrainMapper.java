package com.example.mgtserver.mapper.version2;

import com.example.mgtserver.mapper.version2.provider.TerrainProvider;
import com.example.mgtserver.model.version2.Terrain;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("terrainMapper")
public interface TerrainMapper {
    /**
     * 插入一条信息
     * @param terrain 地形数据模型
     * @return 插入结果
     */
    @InsertProvider(type = TerrainProvider.class, method = "insert")
    Integer insert(Terrain terrain);

    /**
     * 更新模型数据
     * @param terrain 地形数据模型
     * @return 更新结果
     */
    @UpdateProvider(type = TerrainProvider.class, method = "update")
    Integer update(Terrain terrain);

    /**
     * 更新条件查询模型
     * @param terrain 地形数据模型
     * @return 模型列表
     */
    @SelectProvider(type = TerrainProvider.class, method = "query")
    List<Terrain> query(Terrain terrain);

    /**
     * id查询数据
     * @param id 数据id
     * @return 模型数据
     */
    @SelectProvider(type = TerrainProvider.class, method = "queryById")
    Terrain queryById(Long id);

    /**
     * 更新关联osgbId
     *
     * @param terrain 更新目标
     * @return 更新结果
     */
    @UpdateProvider(type = TerrainProvider.class, method = "updateAssociatedOsgbId")
    int updateAssociatedOsgbId(Terrain terrain);
}
