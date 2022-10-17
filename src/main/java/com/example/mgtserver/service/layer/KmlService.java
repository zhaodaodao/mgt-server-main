package com.example.mgtserver.service.layer;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.model.KmlLayer;
import com.example.mgtserver.model.Panorama;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * kml业务接口
 */
public interface KmlService {
    /**
     * 区域上传kml文件
     * @param file 预上传的kml压缩包
     * @return 状态 0 失败，1 成功
     */
    String upload(MultipartFile file)throws Exception;

    /**
     * 移除对应项目id的kml项目
     * @param projectId kml Id
     * @return 状态 0 失败，1 成功
     */
    int removeInProject(Long projectId);

    /**
     * 获取目标项目的所有kml信息
     * @param projectId 项目Id
     * @return 目标区域Kml图层信息列表
     */
    List<KmlLayer> listInProject(Long projectId);

    /**
     * 获取全部kml图层信息
     * @return kml图层信息列表
     */
    List<KmlLayer> listAll();

    /**
     * 删除指定id的kml文件
     * @param id 资源文件id
     * @return 影响行数
     */
    int removeById(Long id);

    /**
     * 更新kml信息
     * @param kmlLayer 资源文件信息
     */
    int update(KmlLayer kmlLayer);

    /**
     * 添加kml资源
     * @param kmlLayer 资源文件信息
     */
    int save(KmlLayer kmlLayer) throws Exception;

    /**
     *
     * @param query 查询条件
     * @return 查询结果
     */
    PageInfo<KmlLayer> list(PagedQueryDTO<KmlLayer> query);

    /**
     * 从项目中获取包装好的url
     * @param projectId 项目id
     * @return
     */
    List<KmlLayer> listFromProjectForShow(Long projectId);
}
