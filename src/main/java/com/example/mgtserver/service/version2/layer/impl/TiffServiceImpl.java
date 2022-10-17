package com.example.mgtserver.service.version2.layer.impl;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.mapper.version2.TiffMapper;
import com.example.mgtserver.model.version2.Tiff;
import com.example.mgtserver.service.project.HostConfigService;
import com.example.mgtserver.service.version2.layer.TiffService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service("tiffService")
public class TiffServiceImpl implements TiffService {
    @Resource(name = "tiffMapper")
    private TiffMapper tiffMapper;

    @Resource(name = "hostConfigService")
    private HostConfigService hostConfigService;

    @Override
    public PageInfo<Tiff> list(PagedQueryDTO<Tiff> queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        Tiff tiff = queryDTO.getQuery();
        tiff.setIsDeleted(0);
        List<Tiff> list = tiffMapper.query(tiff);
        return new PageInfo<>(list);
    }

    @Override
    public int create(Tiff tiff) {
        tiff.setIsDeleted(0);
        tiff.setGmtCreate(System.currentTimeMillis());
        tiff.setGmtModify(System.currentTimeMillis());
        return tiffMapper.insert(tiff);
    }

    @Override
    public int remove(Long id) {
        Tiff tiff = new Tiff();
        tiff.setId(id);
        tiff.setIsDeleted(1);
        tiff.setGmtModify(System.currentTimeMillis());
        return tiffMapper.update(tiff);
    }

    @Override
    public int removeBatch(Long[] ids) {
        return 0;
    }

    @Override
    public Tiff update(Tiff tiff) {
        tiff.setGmtModify(System.currentTimeMillis());
        tiffMapper.update(tiff);
        return tiffMapper.queryById(tiff.getId());
    }

    @Override
    public int updateChecked(Long id, Integer isChecked) {
        Tiff tiff = new Tiff();
        tiff.setId(id);
        tiff.setIsChecked(isChecked);
        tiff.setGmtModify(System.currentTimeMillis());
        return tiffMapper.update(tiff);
    }

    @Override
    public List<Tiff> listByProjectId(Long projectId) {
        Tiff tiff = new Tiff();
        tiff.setProjectId(projectId);
        tiff.setIsDeleted(0);
        return tiffMapper.query(tiff)
                .stream()
                .peek(t -> t.setUrl(hostConfigService.packUrl(t.getUrl())))
                .collect(Collectors.toList());
    }
}
