package com.example.mgtserver.mapper.version2;


import com.example.mgtserver.mapper.version2.provider.TiffProvider;
import com.example.mgtserver.model.version2.Tiff;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("tiffMapper")
public interface TiffMapper {
    /**
     * 插入一条数据
     * @param tiff 卫星影像数据模型
     * @return 插入结果
     */
    @InsertProvider(type = TiffProvider.class, method = "insert")
    Integer insert(Tiff tiff);

    /**
     * 更新数据
     * @param tiff 卫星影像数据模型
     * @return 更新结果
     */
    @UpdateProvider(type = TiffProvider.class, method = "update")
    Integer update(Tiff tiff);

    /**
     * 更新条件查询模型
     * @param tiff 卫星影像数据模型
     * @return 模型列表
     */
    @SelectProvider(type = TiffProvider.class, method = "query")
    List<Tiff> query(Tiff tiff);

    /**
     * 通过id查询
     * @param id 数据模型id
     * @return 卫星图像数据模型
     */
    @SelectProvider(type = TiffProvider.class, method = "queryById")
    Tiff queryById(Long id);
}
