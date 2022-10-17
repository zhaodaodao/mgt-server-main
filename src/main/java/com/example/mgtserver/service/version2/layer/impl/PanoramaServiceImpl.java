package com.example.mgtserver.service.version2.layer.impl;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.mapper.version2.PanoramaMapper;
import com.example.mgtserver.model.Panorama;
import com.example.mgtserver.service.project.HostConfigService;
import com.example.mgtserver.service.version2.layer.PanoramaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hexrt
 * @description 全景图需求V2服务
 * @create 2022-04-09 15:22
 */
@Service("panoramaServiceV2")
public class PanoramaServiceImpl implements PanoramaService {

    @Resource(name = "panoramaMapperV2")
    private PanoramaMapper panoramaMapper;
    @Resource(name = "hostConfigService")
    private HostConfigService hostConfigService;

    @Override
    public PageInfo<Panorama> list(PagedQueryDTO<Panorama> queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        Panorama panorama = queryDTO.getQuery();
        panorama.setIsDeleted(0);
        List<Panorama> list = panoramaMapper.query(panorama);
        return new PageInfo<>(list);
    }

    @Override
    public List<Panorama> listByProjectId(Long projectId) {
        Panorama panorama = new Panorama();
        panorama.setProjectId(projectId);
        panorama.setIsDeleted(0);
        return panoramaMapper.query(panorama)
                .stream()
                .peek(p -> p.setUrl(hostConfigService.packUrl(p.getUrl())))
                .collect(Collectors.toList());
    }

    @Override
    public int create(Panorama panorama) {
        panorama.setIsDeleted(0);
        panorama.setGmtCreate(System.currentTimeMillis());
        panorama.setGmtModify(panorama.getGmtCreate());
        return panoramaMapper.insert(panorama);
    }

    @Override
    public int remove(Long id) {
        Panorama panorama = new Panorama();
        panorama.setId(id);
        panorama.setIsDeleted(1);
        panorama.setGmtModify(System.currentTimeMillis());
        return panoramaMapper.update(panorama);
    }

    @Override
    public int removeBatch(Long[] ids) {
        return 0;
    }

    @Override
    public Panorama update(Panorama panorama) {
        panorama.setGmtModify(System.currentTimeMillis());
        panoramaMapper.update(panorama);
        return panoramaMapper.queryById(panorama.getId());
    }

    @Override
    public int updateChecked(Long id, Integer isChecked) {
        Panorama panorama = new Panorama();
        panorama.setId(id);
        panorama.setIsChecked(isChecked);
        panorama.setGmtModify(System.currentTimeMillis());
        return panoramaMapper.update(panorama);
    }
}
