package com.example.mgtserver.service.layer.impl;

import com.example.mgtserver.dto.layer.OsgbOfProjectDTO;
import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.exception.OsgbException;
import com.example.mgtserver.mapper.OsgbMapper;
import com.example.mgtserver.model.Osgb;
import com.example.mgtserver.model.OsgbOfProject;
import com.example.mgtserver.model.Panorama;
import com.example.mgtserver.service.layer.OsgbService;
import com.example.mgtserver.service.project.HostConfigService;
import com.example.mgtserver.utils.FileUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hexrt
 * @description osgb业务
 * @create 2022-04-03 21:00
 */
@Service("osgbService")
public class OsgbServiceImpl implements OsgbService {
    @Autowired
    private HostConfigService hostConfigService;

    @Value("${project-config.osgb-uri}")
    private String OSGB_URI;

    /**
     * 检验不能为空
     *
     * @param arr
     * @throws OsgbException
     */
    @SafeVarargs
    private final <V> void notNull(V... arr) throws OsgbException {
        for (V v : arr) {
            if (null == v) {
                throw new OsgbException("参数不能为空");
            }
        }
    }

    @Resource(name = "osgbMapper")
    private OsgbMapper osgbMapper;

    @Override
    public Long save(Osgb osgb) throws OsgbException {
        if (null == osgb.getUrl() || !FileUtil.exist(osgb.getUrl())) {
            throw new OsgbException("当前目录不存在 " + osgb.getUrl());
        }
        //todo 注意地址
        String url = hostConfigService.unpackUrl(osgb.getUrl(), OSGB_URI);
        Osgb existOsgb = osgbMapper.fetchByUrl(url);
        if (null == existOsgb) {
            osgb.setUrl(url);
            osgbMapper.insert(osgb.getUrl(), osgb.getName(), System.currentTimeMillis());
            existOsgb = osgbMapper.fetchByUrl(url);
        }
        if (null == existOsgb) {
            throw new OsgbException("添加模型库失败");
        }
        return existOsgb.getId();
    }

    @Override
    public int remove(long id) {
        return osgbMapper.delete(id);
    }

    @Override
    public Osgb update(Osgb osgb) throws OsgbException {
        if (null == osgb) {
            throw new OsgbException("osgb模型信息不能为空");
        }
        if (null == osgb.getId()) {
            throw new OsgbException("osgb的id不能为空");
        }
        osgbMapper.update(osgb);
        return osgbMapper.fetchById(osgb.getId());
    }

    @Override
    public PageInfo<Osgb> list(PagedQueryDTO<Osgb> queryDTO) throws OsgbException {
        Osgb osgb = queryDTO.getQuery();
        notNull(osgb);
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<Osgb> list = osgbMapper.fetch(osgb);
        return new PageInfo<>(list);
    }

    @Override
    public List<Osgb> listAll() {
        return osgbMapper.fetchAll();
    }

    @Override
    public Integer addToProjectPlus(OsgbOfProjectDTO osgbOfProjectDTO) throws OsgbException {
        notNull(osgbOfProjectDTO);
        notNull(osgbOfProjectDTO.getUrl(), osgbOfProjectDTO.getProjectId());
        Long osgbId = save(new Osgb(null, osgbOfProjectDTO.getUrl(), osgbOfProjectDTO.getName(), null, null, null));
        osgbOfProjectDTO.setOsgbId(osgbId);
        return addToProject(
                new OsgbOfProject(null,
                        osgbOfProjectDTO.getProjectId(),
                        osgbOfProjectDTO.getOsgbId(),
                        osgbOfProjectDTO.getIsChecked(),
                        null,
                        System.currentTimeMillis(),
                        System.currentTimeMillis()));
    }

    @Override
    public int addToProject(OsgbOfProject osgbOfProject) throws OsgbException {
        notNull(osgbOfProject, osgbOfProject.getOsgbId(), osgbOfProject.getProjectId());
        OsgbOfProject existOsgbOfProject = osgbMapper.fetchByOsgbProject(osgbOfProject.getProjectId(), osgbOfProject.getOsgbId());
        if (null != existOsgbOfProject) {
            throw new OsgbException("不能重复添加");
        }
        return osgbMapper.insertToProject(osgbOfProject);
    }

    /**
     * 检验对于关系模型更新的数据是否合法
     *
     * @param osgbOfProjectDTO 关系模型
     * @throws OsgbException
     */
    private void validForProjectUpdate(OsgbOfProjectDTO osgbOfProjectDTO) throws OsgbException {
        notNull(osgbOfProjectDTO);
        if (null == osgbOfProjectDTO.getId()) {
            if (null == osgbOfProjectDTO.getProjectId() || null == osgbOfProjectDTO.getOsgbId()) {
                throw new OsgbException("无法确定对应的模型");
            }
        }
    }

    @Override
    public int removeFromProject(OsgbOfProjectDTO osgbOfProjectDTO) throws OsgbException {
        validForProjectUpdate(osgbOfProjectDTO);
        return osgbMapper.removeFromProject(osgbOfProjectDTO);
    }

    @Override
    public Integer updateFromProject(OsgbOfProjectDTO osgbOfProjectDTO) throws OsgbException {
        validForProjectUpdate(osgbOfProjectDTO);
        //todo 改为传回值为OsgbOfProjectDTO
        return osgbMapper.updateFromProject(osgbOfProjectDTO);
    }

    @Override
    public PageInfo<OsgbOfProjectDTO> listFromProject(PagedQueryDTO<OsgbOfProjectDTO> queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<OsgbOfProjectDTO> list = osgbMapper.fetchFromProject(queryDTO.getQuery());
        return new PageInfo<>(list);
    }

    @Override
    public List<OsgbOfProjectDTO> listFromProjectForShow(Long projectId) {
        List<OsgbOfProjectDTO> list = osgbMapper.fetchByProjectId(projectId);
        for (OsgbOfProjectDTO osgbOfProjectDTO : list) {
            osgbOfProjectDTO.setUrl(hostConfigService.packUrl(osgbOfProjectDTO.getUrl()));
        }
        return list;
    }
}
