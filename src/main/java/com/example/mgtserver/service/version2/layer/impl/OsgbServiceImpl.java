package com.example.mgtserver.service.version2.layer.impl;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.mapper.version2.OsgbMapper;
import com.example.mgtserver.model.version2.Osgb;
import com.example.mgtserver.service.project.HostConfigService;
import com.example.mgtserver.service.version2.layer.OsgbService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hexrt
 * @description
 * @create 2022-04-09 15:21
 */
@Service("osgbServiceV2")
public class OsgbServiceImpl implements OsgbService {

    @Resource(name = "osgbMapperV2")
    private OsgbMapper osgbMapper;
    @Resource(name = "hostConfigService")
    private HostConfigService hostConfigService;

    @Override
    public PageInfo<Osgb> list(PagedQueryDTO<Osgb> queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        Osgb osgb = queryDTO.getQuery();
        osgb.setIsDeleted(0);
        List<Osgb> list = osgbMapper.query(osgb);
        return new PageInfo<>(list);
    }

    @Override
    public List<Osgb> listByProjectId(Long projectId) {
        Osgb osgb = new Osgb();
        osgb.setProjectId(projectId);
        osgb.setIsDeleted(0);
        return osgbMapper.query(osgb)
                .stream()
                .peek(o -> o.setUrl(hostConfigService.packUrl(o.getUrl())))
                .collect(Collectors.toList());
    }

    @Override
    public int create(Osgb osgb) {
        osgb.setIsDeleted(0);
        osgb.setGmtCreate(System.currentTimeMillis());
        osgb.setGmtModify(osgb.getGmtCreate());
        return osgbMapper.insert(osgb);
    }

    @Override
    public int remove(Long id) {
        Osgb osgb = new Osgb();
        osgb.setId(id);
        osgb.setIsDeleted(1);
        osgb.setGmtModify(System.currentTimeMillis());
        return osgbMapper.update(osgb);
    }

    @Override
    public int removeBatch(Long[] ids) {
        return 0;
    }

    @Override
    public Osgb update(Osgb osgb) {
        osgb.setGmtModify(System.currentTimeMillis());
        osgbMapper.update(osgb);
        return osgbMapper.queryById(osgb.getId());
    }

    @Override
    public int updateChecked(Long id, Integer isChecked) {
        Osgb osgb = new Osgb();
        osgb.setId(id);
        osgb.setIsChecked(isChecked);
        osgb.setGmtModify(System.currentTimeMillis());
        return osgbMapper.update(osgb);
    }
}
