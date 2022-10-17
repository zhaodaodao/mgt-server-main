package com.example.mgtserver.mapper.version2;

import com.example.mgtserver.mapper.version2.provider.ArtificialLayerProvider;
import com.example.mgtserver.model.ArtificialLayer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hexrt
 * @description
 * @create 2022-04-09 15:29
 */
@Repository("artificialLayerMapperV2")
@Mapper
public interface ArtificialLayerMapper {
    /**
     * 插入一条模型信息
     * @param artificialLayer 模型数据
     * @return 插入结果
     */
    @InsertProvider(type = ArtificialLayerProvider.class, method = "insert")
    Integer insert(ArtificialLayer artificialLayer);

    /**
     * 更新模型数据
     * @param artificialLayer 模型数据
     * @return 更新结果
     */
    @UpdateProvider(type = ArtificialLayerProvider.class, method = "update")
    Integer update(ArtificialLayer artificialLayer);

    /**
     * 更新条件查询模型
     * @param artificialLayer 条件模型数据
     * @return 模型列表
     */
    @SelectProvider(type = ArtificialLayerProvider.class, method = "query")
    List<ArtificialLayer> query(ArtificialLayer artificialLayer);

    /**
     * 通过模型id查询模型数据
     * @param id 模型id
     * @return 模型数据
     */
    @SelectProvider(type = ArtificialLayerProvider.class, method = "queryById")
    ArtificialLayer queryById(Long id);
}
