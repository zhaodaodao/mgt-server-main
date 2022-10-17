package com.example.mgtserver.mapper;

import com.example.mgtserver.mapper.provider.KmlProvider;
import com.example.mgtserver.model.KmlLayer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author qjh
 */

@Mapper
@Repository("kmlLayerMapper")
public interface KmlLayerMapper {

    /**
     *
     * @param projectId    kml资源所属项目的id
     * @param url       kml资源文件的url地址
     * @param name      kml文件的名字
     * @param isChecked kml文件上次勾选状态
     * @param currentTime   文件上传时间(当前时间)
     * @return  影响行数
     */
    @Insert("INSERT INTO kml_layer (project_id, url, name,is_checked,description, gmt_create, gmt_modify) " +
            "VALUES (#{projectId}, #{url}, #{name},#{isChecked},#{description}, #{currentTime}, #{currentTime})")
    Integer insert(Long projectId, String url, String name, Integer isChecked,String description, Long currentTime);

    /**
     * 通过项目id获取对应区域kml文件信息数据
     * @param projectId 项目id
     * @return 项目id对应所有kml信息
     */
    @Select("SELECT * FROM kml_layer WHERE project_id = #{projectId} AND is_deleted = 0 ORDER BY gmt_modify DESC")
    List<KmlLayer> fetchByProjectId(Long projectId);

    /**
     * 获取所有kml文件信息
     * @return 所有kml文件信息
     */
    @Select("SELECT * FROM kml_layer WHERE is_deleted = 0 ORDER BY gmt_modify DESC")
    List<KmlLayer> fetchAll();

    /**
     * 根据id删除kml数据
     * @param id kml文件id
     * @return 影响行数
     */
    @Update("UPDATE kml_layer SET is_deleted = 1, gmt_modify = #{currentTime} WHERE id = #{id} AND is_deleted = 0")
    Integer delete(Long id, Long currentTime);

    /**
     * 删除对应项目id所有kml数据
     * @param projectId 项目id
     * @return 影响行数
     */
    @Update("UPDATE kml_layer SET is_deleted = 1, gmt_modify = #{currentTime} WHERE project_id = #{projectId} AND is_deleted = 0")
    Integer deleteAll(Long projectId, Long currentTime);


    /**
     * 更新kml资源文件信息
     * @param kmlLayer kml资源更新信息
     * @return 影响行数
     */
    @UpdateProvider(type = KmlProvider.class,method = "updateKml")
    Integer update(KmlLayer kmlLayer);

    @SelectProvider(type = KmlProvider.class,method = "fetch")
    List<KmlLayer> fetch(KmlLayer kmlLayer);
}
