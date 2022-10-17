package com.example.mgtserver.service.version2.layer;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.model.version2.Tiff;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 卫星影像图业务接口
 */
public interface TiffService {
    /**
     * 分页查询卫星影像图
     * @param queryDTO 询问
     * @return 分页查询结果
     */
    PageInfo<Tiff> list(PagedQueryDTO<Tiff> queryDTO);

    /**
     * 新增卫星影像数据模型
     * @param tiff 卫星影像数据模型
     * @return 新增结果
     * //todo 返回值待定
     */
    int create(Tiff tiff);

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
     * @param tiff 人工模型数据
     * @return 更新后的数据
     */
    Tiff update(Tiff tiff);

    /**
     * 更新默认加载
     * @param id        修改的id
     * @param isChecked 是否默认加载
     * @return 修改结果
     */
    int updateChecked(Long id, Integer isChecked);

    /**
     * 根据项目id获取卫星影像数据
     * @param projectId 项目id
     * @return 影像数据列表
     */
    List<Tiff> listByProjectId(Long projectId);
}
