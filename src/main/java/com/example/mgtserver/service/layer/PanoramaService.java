package com.example.mgtserver.service.layer;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.exception.PanoramaException;
import com.example.mgtserver.model.Panorama;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 全景图数据业务接口
 *
 * @author Hexrt
 */
public interface PanoramaService {
    /**
     * 上传全景图项目
     *
     * @param file       全景图压缩文件
     * @return 状态 0 失败，1 成功
     * @throws IOException 上传文件失败
     */
    String uploadFile(MultipartFile file) throws Exception;

    /**
     * 上传全景图信息（在上传文件之后）
     * @param panorama  全景图信息
     * @return 上传结果<br>
     * 0 : 信息无效<br>
     * 1 : 成功<br>
     * 2 : 全景图不存在<br>
     * 3 : 其他异常<br>
     * // todo
     * @throws Exception 多种错误
     * //todo 完善错误类型
     */
    int uploadInfo(Panorama panorama) throws Exception;

    /**
     * 从区域中移除全景图项目
     *
     * @param panoramaId 目标全景图Id
     * @return 状态 0 失败，1 成功
     */
    int remove(Long panoramaId);

    /**
     * 列出目标区域全景图
     *
     * @param project 目标项目Id
     * @deprecated 历史遗留问题
     * @return 全景图列表
     */
    @Deprecated
    List<Panorama> listInProject(Long project);

    /**
     * 列出全部全景图
     *
     * @return 全景图列表
     * @deprecated 历史遗留业务，不提供分页，以及条件查询，给出所有的全景图像信息
     */
    @Deprecated
    List<Panorama> listAll();

    /**
     * 条件分页查询
     * @param query 查询体
     * @return 分页查询结果
     */
    PageInfo<Panorama> list(PagedQueryDTO<Panorama> query);

    /**
     * 更新全景图
     * @param panorama 全景图信息
     * @return 更新后的信息
     */
    Panorama update(Panorama panorama) throws PanoramaException;

    /**
     * 从项目中获取全景图信息
     * @param projectId 项目id
     * @return
     */
    List<Panorama> listFromProjectForShow(Long projectId);
}
