package com.example.mgtserver.service.layer;

import com.example.mgtserver.dto.layer.OsgbOfProjectDTO;
import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.exception.OsgbException;
import com.example.mgtserver.model.Osgb;
import com.example.mgtserver.model.OsgbOfProject;
import com.example.mgtserver.model.Panorama;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Hexrt
 * @description osgb模型业务
 * @create 2022-03-12 20:20
 */
public interface OsgbService {
    /**
     * 保存osgb模型文件
     * @param osgb 保存模型
     * @return 状态 0 失败，1 成功
     */
    Long save(Osgb osgb) throws OsgbException;

    /**
     * 删除osgb
     * @param id osgb模型id
     * @return
     */
    int remove(long id);

    /**
     * 更新osgb模型信息
     * @param osgb osgb模型信息
     * @return 修改后的信息
     */
    Osgb update(Osgb osgb) throws OsgbException;

    /**
     * 条件分页查询
     * @param queryDTO 查询体
     * @return 查询结果
     */
    PageInfo<Osgb> list(PagedQueryDTO<Osgb> queryDTO) throws OsgbException;

    /**
     * 列出全部osgb信息
     * @deprecated 历史遗留代码
     * @return 区域中osgb信息数据封装对象
     */
    @Deprecated
    List<Osgb> listAll();

    /**
     * 添加osgb模型文件至目标区域
     *
     * @param osgbOfProject 模型关系
     * @return 状态 0 失败，1 成功
     */
    int addToProject(OsgbOfProject osgbOfProject) throws OsgbException;

    /**
     * 从区域中移除osgb项目
     *
     * @param osgbOfProjectDTO osgb关系模型
     * @return 状态 0 失败，1 成功
     */
    int removeFromProject(OsgbOfProjectDTO osgbOfProjectDTO) throws OsgbException;

    /**
     * 更新osgb关系模型信息
     * @param osgbOfProjectDTO osgb关系模型
     * @return 修改后的关系模型
     */
    Integer updateFromProject(OsgbOfProjectDTO osgbOfProjectDTO) throws OsgbException;

    /**
     * 列出目标区域的osgb项目信息
     *
     * @param queryDTO 分页查询请求
     * @return 分页包装osgb信息
     */
    PageInfo<OsgbOfProjectDTO> listFromProject(PagedQueryDTO<OsgbOfProjectDTO> queryDTO);

    /**
     * 直接添加osgb模型到项目中
     * @param osgbOfProjectDTO osgb关系模型
     * @return 添加结果
     */
    Integer addToProjectPlus(OsgbOfProjectDTO osgbOfProjectDTO) throws OsgbException;

    /**
     * 从项目中获取osgb模型数据
     * @param projectId  项目id
     * @return
     */
    List<OsgbOfProjectDTO> listFromProjectForShow(Long projectId);
}
