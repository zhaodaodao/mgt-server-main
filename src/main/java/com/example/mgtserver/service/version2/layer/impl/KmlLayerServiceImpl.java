package com.example.mgtserver.service.version2.layer.impl;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.mapper.version2.KmlLayerMapper;
import com.example.mgtserver.model.KmlLayer;
import com.example.mgtserver.service.project.HostConfigService;
import com.example.mgtserver.service.version2.layer.KmlLayerService;
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
@Service("kmlLayerServiceV2")
public class KmlLayerServiceImpl implements KmlLayerService {
    @Resource(name = "kmlLayerMapperV2")
    private KmlLayerMapper kmlLayerMapper;
    @Resource(name = "hostConfigService")
    private HostConfigService hostConfigService;

    @Override
    public PageInfo<KmlLayer> list(PagedQueryDTO<KmlLayer> queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        KmlLayer kmlLayer = queryDTO.getQuery();
        kmlLayer.setIsDeleted(0);
        List<KmlLayer> list = kmlLayerMapper.query(kmlLayer);
        return new PageInfo<>(list);
    }

    @Override
    public List<KmlLayer> listByProjectId(Long projectId) {
        KmlLayer kmlLayer = new KmlLayer();
        kmlLayer.setProjectId(projectId);
        kmlLayer.setIsDeleted(0);
        return kmlLayerMapper.query(kmlLayer)
                .stream()
                .peek(kl -> kl.setUrl(hostConfigService.packUrl(kl.getUrl())))
                .collect(Collectors.toList());
    }

    @Override
    public int create(KmlLayer kmlLayer) {
        kmlLayer.setIsDeleted(0);
        kmlLayer.setGmtCreate(System.currentTimeMillis());
        kmlLayer.setGmtModify(kmlLayer.getGmtCreate());
        return kmlLayerMapper.insert(kmlLayer);
    }

    @Override
    public int remove(Long id) {
        KmlLayer kmlLayer = new KmlLayer();
        kmlLayer.setId(id);
        kmlLayer.setIsDeleted(1);
        kmlLayer.setGmtModify(System.currentTimeMillis());
        return kmlLayerMapper.update(kmlLayer);
    }

    @Override
    public int removeBatch(Long[] ids) {
        return 0;
    }

    @Override
    public KmlLayer update(KmlLayer kmlLayer) {
        kmlLayer.setGmtModify(System.currentTimeMillis());
        kmlLayerMapper.update(kmlLayer);
        return kmlLayerMapper.queryById(kmlLayer.getId());
    }

    @Override
    public int updateChecked(Long id, Integer isChecked) {
        KmlLayer kmlLayer = new KmlLayer();
        kmlLayer.setId(id);
        kmlLayer.setIsChecked(isChecked);
        kmlLayer.setGmtModify(System.currentTimeMillis());
        return kmlLayerMapper.update(kmlLayer);
    }
}
