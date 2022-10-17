package com.example.mgtserver.service.layer;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.exception.ArtificialLayerException;
import com.example.mgtserver.model.ArtificialLayer;
import com.example.mgtserver.model.Panorama;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 人工建模图层业务接口
 *
 * @author Hexrt
 */
public interface ArtificialLayerService {
    /**
     * 区域上传人工建模图层项目
     *
     * @param file   上传的kml文件
     * @return 临时文件名
     */
    String uploadFile(MultipartFile file) throws Exception;

    /**
     * 上传信息（上传文件之后）
     * @param artificialLayer 人工模型数据
     * @return 上传状态
     */
    int uploadInfo(ArtificialLayer artificialLayer) throws Exception;

    /**
     * 从区域中移除人工建模图层项目
     *
     * @param artificialLayerId 人工模型数据id
     * @return 状态 0 失败，1 成功
     */
    int remove(Long artificialLayerId) throws ArtificialLayerException;

    /**
     * 更新人工模型数据
     * @param artificialLayer 人工模型数据
     * @return
     */
    ArtificialLayer update(ArtificialLayer artificialLayer) throws ArtificialLayerException;

    /**
     * 条件分页查询
     * @param queryDTO 查询
     * @return
     */
    PageInfo<ArtificialLayer> list(PagedQueryDTO<ArtificialLayer> queryDTO) throws ArtificialLayerException;

    /**
     * 获取所有人工建模图层项目
     *
     * @return 人工建模图层项目列表
     */
    @Deprecated
    List<ArtificialLayer> listAll();

    /**
     * 从项目中抽取所有模型
     * @param projectId
     * @return
     */
    List<ArtificialLayer> listFromProjectForShow(Long projectId);
}
