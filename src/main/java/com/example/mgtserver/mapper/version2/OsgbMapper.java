package com.example.mgtserver.mapper.version2;

import com.example.mgtserver.mapper.version2.provider.OsgbProvider;
import com.example.mgtserver.model.version2.Osgb;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hexrt
 * @description
 * @create 2022-04-09 15:27
 */
@Repository("osgbMapperV2")
@Mapper
public interface OsgbMapper {
    /**
     * 插入一条模型信息
     * @param osgb 模型数据
     * @return 插入结果
     */
    @InsertProvider(type = OsgbProvider.class, method = "insert")
    Integer insert(Osgb osgb);

    /**
     * 更新模型数据
     * @param osgb 模型数据
     * @return 更新结果
     */
    @UpdateProvider(type = OsgbProvider.class, method = "update")
    Integer update(Osgb osgb);

    /**
     * 更新条件查询模型
     * @param osgb 条件模型数据
     * @return 模型列表
     */
    @SelectProvider(type = OsgbProvider.class, method = "query")
    List<Osgb> query(Osgb osgb);

    /**
     * 通过模型id查询模型数据
     * @param id 模型id
     * @return 模型数据
     */
    @SelectProvider(type = OsgbProvider.class, method = "queryById")
    Osgb queryById(Long id);
}
