package com.example.mgtserver.service.version2.layer.impl;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.mapper.version2.TerrainMapper;
import com.example.mgtserver.model.version2.Terrain;
import com.example.mgtserver.service.project.HostConfigService;
import com.example.mgtserver.service.version2.layer.TerrainService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("terrainService")
public class TerrainServiceImpl implements TerrainService {
    @Resource(name = "terrainMapper")
    private TerrainMapper terrainMapper;

    @Resource(name = "hostConfigService")
    private HostConfigService hostConfigService;

    @Override
    public PageInfo<Terrain> list(PagedQueryDTO<Terrain> queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        Terrain terrain = queryDTO.getQuery();
        terrain.setIsDeleted(0);
        List<Terrain> list = terrainMapper.query(terrain);
        return new PageInfo<>(list);
    }

    @Override
    public int create(Terrain terrain) {
        terrain.setIsDeleted(0);
        terrain.setGmtCreate(System.currentTimeMillis());
        terrain.setGmtModify(System.currentTimeMillis());
        return terrainMapper.insert(terrain);
    }

    @Override
    public int remove(Long id) {
        Terrain terrain = new Terrain();
        terrain.setId(id);
        terrain.setIsDeleted(1);
        terrain.setGmtModify(System.currentTimeMillis());
        return terrainMapper.update(terrain);
    }

    @Override
    public int removeBatch(Long[] ids) {
        return 0;
    }

    @Override
    public Terrain update(Terrain terrain) {
        terrain.setGmtModify(System.currentTimeMillis());
        terrainMapper.update(terrain);
        return terrainMapper.queryById(terrain.getId());
    }

    @Override
    public int updateChecked(Long id, Integer isChecked) {
        Terrain terrain = new Terrain();
        terrain.setId(id);
        terrain.setIsChecked(isChecked);
        terrain.setGmtModify(System.currentTimeMillis());
        return terrainMapper.update(terrain);
    }

    @Override
    public List<Terrain> listByProjectId(Long projectId) {
        Terrain terrain = new Terrain();
        terrain.setIsDeleted(0);
        terrain.setProjectId(projectId);
        return terrainMapper.query(terrain)
                .stream()
                .peek(t -> t.setUrl(hostConfigService.packUrl(t.getUrl())))
                .collect(Collectors.toList());
    }

    @Override
    public int updateAssociatedOsgb(Terrain terrain) {
        Terrain associatedOsgbQuery = new Terrain();

        // 若待修改的terrain关联osgb字段不为空，则查找
        if (null != terrain.getAssociatedOsgbId()) {
            associatedOsgbQuery.setAssociatedOsgbId(terrain.getAssociatedOsgbId());
            List<Terrain> result = terrainMapper.query(associatedOsgbQuery);
            // 查找结果不为空
            if (null != result && !result.isEmpty()) {
                result.forEach(terrain1 -> {
                    // 查找到的terrain目标不是当前目标则更新
                    if (!terrain.getId().equals(terrain1.getId())) {
                        terrain1.setGmtModify(System.currentTimeMillis());
                        // 解除已有关联
                        terrain1.setAssociatedOsgbId(null);
                        terrainMapper.updateAssociatedOsgbId(terrain1);
                    }
                });
            }
        }

        terrain.setGmtModify(System.currentTimeMillis());
        return terrainMapper.updateAssociatedOsgbId(terrain);
    }

    @Override
    public int dissociateOsgb(Long osgbId) {
        Terrain terrain = new Terrain();
        terrain.setAssociatedOsgbId(osgbId);
        List<Terrain> result = terrainMapper.query(terrain);

        result.forEach(terrain1 -> {
            terrain1.setGmtModify(System.currentTimeMillis());
            terrain1.setAssociatedOsgbId(null);
            terrainMapper.updateAssociatedOsgbId(terrain1);
        });

        return 1;
    }
}
