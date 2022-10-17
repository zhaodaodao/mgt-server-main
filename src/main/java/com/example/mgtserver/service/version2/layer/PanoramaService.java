package com.example.mgtserver.service.version2.layer;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.model.Panorama;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Hexrt
 * @description 全景图需求版本V2Service
 * @create 2022-04-08 19:58
 */
public interface PanoramaService {
    /**
     * 分页查询全景图
     * @param queryDTO 询问
     * @return 分页查询结果
     */
    PageInfo<Panorama> list(PagedQueryDTO<Panorama> queryDTO);

    /**
     * 通过项目id获取所有数据（不分页）
     * @param projectId 项目id
     * @return 项目中的模型数据
     */
    List<Panorama> listByProjectId(Long projectId);

    /**
     * 新增模型
     * @param panorama 模型数据
     * @return 新增结果
     * //todo 返回值待定
     */
    int create(Panorama panorama);

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
     * @param panorama 全景图模型数据
     * @return 更新后的数据
     */
    Panorama update(Panorama panorama);

    /**
     * 更新默认加载
     * @param id        修改的id
     * @param isChecked 是否默认加载
     * @return 修改结果
     */
    int updateChecked(Long id, Integer isChecked);
}
