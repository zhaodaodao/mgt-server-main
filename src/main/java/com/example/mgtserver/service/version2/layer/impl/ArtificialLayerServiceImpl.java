package com.example.mgtserver.service.version2.layer.impl;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.mapper.version2.ArtificialLayerMapper;
import com.example.mgtserver.model.ArtificialLayer;
import com.example.mgtserver.service.project.HostConfigService;
import com.example.mgtserver.service.version2.layer.ArtificialLayerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hexrt
 * @description 人工模型服务
 * @create 2022-04-08 19:38
 */
@Service("artificialLayerServiceV2")
public class ArtificialLayerServiceImpl implements ArtificialLayerService {
    @Resource(name = "artificialLayerMapperV2")
    private ArtificialLayerMapper artificialLayerMapper;
    @Resource(name = "hostConfigService")
    private HostConfigService hostConfigService;

    @Override
    public PageInfo<ArtificialLayer> list(PagedQueryDTO<ArtificialLayer> queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        ArtificialLayer artificialLayer = queryDTO.getQuery();
        artificialLayer.setIsDeleted(0);
        List<ArtificialLayer> list = artificialLayerMapper.query(artificialLayer);
        return new PageInfo<>(list);
    }

    @Override
    public List<ArtificialLayer> listByProjectId(Long projectId) {
        ArtificialLayer artificialLayer = new ArtificialLayer();
        artificialLayer.setProjectId(projectId);
        artificialLayer.setIsDeleted(0);
        return artificialLayerMapper.query(artificialLayer)
                .stream()
                .peek(al -> al.setUrl(hostConfigService.packUrl(al.getUrl())))
                .collect(Collectors.toList());
    }

    @Override
    public int create(ArtificialLayer artificialLayer) {
        artificialLayer.setIsDeleted(0);
        artificialLayer.setGmtCreate(System.currentTimeMillis());
        artificialLayer.setGmtModify(artificialLayer.getGmtCreate());
        return artificialLayerMapper.insert(artificialLayer);
    }

    @Override
    public int remove(Long id) {
        ArtificialLayer artificialLayer = new ArtificialLayer();
        artificialLayer.setId(id);
        artificialLayer.setIsDeleted(1);
        artificialLayer.setGmtModify(System.currentTimeMillis());
        return artificialLayerMapper.update(artificialLayer);
    }

    @Override
    public int removeBatch(Long[] ids) {
        return 0;
    }

    @Override
    public ArtificialLayer update(ArtificialLayer artificialLayer) {
        //todo 做出对修改的限制，禁止更新url，projectId属性等
        artificialLayer.setGmtModify(System.currentTimeMillis());
        artificialLayerMapper.update(artificialLayer);
        return artificialLayerMapper.queryById(artificialLayer.getId());
    }

    @Override
    public int updateChecked(Long id, Integer isChecked) {
        ArtificialLayer artificialLayer = new ArtificialLayer();
        artificialLayer.setId(id);
        artificialLayer.setIsChecked(isChecked);
        artificialLayer.setGmtModify(System.currentTimeMillis());
        return artificialLayerMapper.update(artificialLayer);
    }
}
