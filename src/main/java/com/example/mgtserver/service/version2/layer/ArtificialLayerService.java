package com.example.mgtserver.service.version2.layer;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.model.ArtificialLayer;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Hexrt
 * @description 人工模型需求V2版本
 * @create 2022-04-08 19:36
 */
public interface ArtificialLayerService {
    /**
     * 分页查询人工模型
     * @param queryDTO 询问
     * @return 分页查询结果
     */
    PageInfo<ArtificialLayer> list(PagedQueryDTO<ArtificialLayer> queryDTO);

    /**
     * 通过项目id获取所有数据（不分页）
     * @param projectId 项目id
     * @return 项目中的模型数据
     */
    List<ArtificialLayer> listByProjectId(Long projectId);

    /**
     * 新增模型
     * @param artificialLayer 模型数据
     * @return 新增结果
     * //todo 返回值待定
     */
    int create(ArtificialLayer artificialLayer);

    /**
     * 删除模型
     * @param id 模型id
     * @return 删除模型结果
     */
    int remove(Long id);

    /**
     * 批量删除
     * @param ids 删除的id数组
     * @return
     * //todo 涉及事务
     */
    int removeBatch(Long[] ids);

    /**
     * 更新模型数据
     * @param artificialLayer 人工模型数据
     * @return 更新后的数据
     */
    ArtificialLayer update(ArtificialLayer artificialLayer);

    /**
     * 更新默认加载
     * @param id        修改的id
     * @param isChecked 是否默认加载
     * @return 修改结果
     */
    int updateChecked(Long id, Integer isChecked);
}
