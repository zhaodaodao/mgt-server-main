package com.example.mgtserver.service.version2.layer;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.model.version2.Terrain;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TerrainService {
    /**
     * 分页查询地形数据模型
     * @param queryDTO 询问
     * @return 分页查询结果
     */
    PageInfo<Terrain> list(PagedQueryDTO<Terrain> queryDTO);

    /**
     * 新增地形数据模型
     * @param terrain 地形数据模型
     * @return 新增结果
     * //todo 返回值待定
     */
    int create(Terrain terrain);

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
     * @param terrain 人工模型数据
     * @return 更新后的数据
     */
    Terrain update(Terrain terrain);

    /**
     * 更新默认加载
     * @param id        修改的id
     * @param isChecked 是否默认加载
     * @return 修改结果
     */
    int updateChecked(Long id, Integer isChecked);

    /**
     * 根据项目id列出地形数据列表
     * @param projectId 项目id
     * @return 地形数据列表
     */
    List<Terrain> listByProjectId(Long projectId);

    /**
     * 更新关联osgb
     * @param terrain 待更新的terrain
     * @return 更新结果
     */
    int updateAssociatedOsgb(Terrain terrain);

    /**
     * 解除osgb关联
     * @param osgbId 解除目标id
     * @return 解除结果 1 成功
     */
    int dissociateOsgb(Long osgbId);
}
