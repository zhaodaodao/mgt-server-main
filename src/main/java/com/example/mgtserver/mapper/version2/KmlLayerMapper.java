package com.example.mgtserver.mapper.version2;

import com.example.mgtserver.mapper.version2.provider.KmlLayerProvider;
import com.example.mgtserver.model.KmlLayer;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hexrt
 * @description Kml模型持久化
 * @create 2022-04-09 15:24
 */
@Repository("kmlLayerMapperV2")
@Mapper
public interface KmlLayerMapper {
    /**
     * 插入一条模型信息
     * @param kmlLayer 模型数据
     * @return 插入结果
     */
    @InsertProvider(type = KmlLayerProvider.class, method = "insert")
    Integer insert(KmlLayer kmlLayer);

    /**
     * 更新模型数据
     * @param kmlLayer 模型数据
     * @return 更新结果
     */
    @UpdateProvider(type = KmlLayerProvider.class, method = "update")
    Integer update(KmlLayer kmlLayer);

    /**
     * 更新条件查询模型
     * @param kmlLayer 条件模型数据
     * @return 模型列表
     */
    @SelectProvider(type = KmlLayerProvider.class, method = "query")
    List<KmlLayer> query(KmlLayer kmlLayer);

    /**
     * 通过模型id查询模型数据
     * @param id 模型id
     * @return 模型数据
     */
    @SelectProvider(type = KmlLayerProvider.class, method = "queryById")
    KmlLayer queryById(Long id);
}
