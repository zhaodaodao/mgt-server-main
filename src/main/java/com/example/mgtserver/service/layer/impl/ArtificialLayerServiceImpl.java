package com.example.mgtserver.service.layer.impl;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.exception.ArtificialLayerException;
import com.example.mgtserver.exception.OsgbException;
import com.example.mgtserver.exception.PanoramaException;
import com.example.mgtserver.mapper.ArtificialLayerMapper;
import com.example.mgtserver.model.ArtificialLayer;
import com.example.mgtserver.model.Panorama;
import com.example.mgtserver.service.layer.ArtificialLayerService;
import com.example.mgtserver.service.project.HostConfigService;
import com.example.mgtserver.utils.FileUtil;
import com.example.mgtserver.utils.MultipartFileUtil;
import com.example.mgtserver.utils.ZipUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service("artificialLayerService")
public class ArtificialLayerServiceImpl implements ArtificialLayerService {
    @Resource(name = "artificialLayer")
    private ArtificialLayerMapper artificialLayerMapper;
    @Autowired
    private HostConfigService hostConfigService;
    @Value("${project-config.base-location}")
    private String BASE_LOCATION;
    @Value("${project-config.artificial-uri}")
    private String ARTIFICIAL_LAYER_URI;

    /**
     * 默认接受文件类型
     */
    private final String DEFAULT_FILE_TYPE = ".zip";
    /**
     * 默认接受文件标识符文件名
     */
    private final String DEFAULT_FILE_PATTERN = "tileset.json";

    /**
     * 获取当前项目的人工模型存储路径
     * @return 存储路径
     */
    private String artificialLocation() {
        return (BASE_LOCATION + ARTIFICIAL_LAYER_URI).intern();
    }

    /**
     * 检验不能为空
     * @param arr
     * @throws OsgbException
     */
    @SafeVarargs
    private final <V> void notNull(V... arr) throws ArtificialLayerException {
        for (V v : arr) {
            if (null == v) {
                throw new ArtificialLayerException("参数不能为空");
            }
        }
    }

    /**
     * 检查文件合法性
     * 检查基本信息，后缀为zip
     * @param file 传入的文件
     */
    private void validFile(MultipartFile file) throws ArtificialLayerException {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        if (!suffix.trim().equals(DEFAULT_FILE_TYPE)) {
            throw new ArtificialLayerException("文件类型非法，需要类型：" + DEFAULT_FILE_TYPE + " 给定的为：" + suffix);
        }
    }


    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        //todo 检验数据合法
        String url = MultipartFileUtil.upload(file, artificialLocation());
        if (null == url) {
            throw new ArtificialLayerException("人工模型上传失败");
        }
        return url;
    }

    @Override
    public int uploadInfo(ArtificialLayer artificialLayer) throws Exception {
        //todo 检查参数合法性
        notNull(artificialLayer);
        notNull(artificialLayer.getName(), artificialLayer.getProjectId(), artificialLayer.getUrl());

        if (artificialLayer.getUrl() == null || !FileUtil.exist(
                artificialLocation() + File.separator + artificialLayer.getUrl())) {
            //todo 检验是否有此文件
            throw new PanoramaException("预上传文件未给定");
        }

        String artificialLayerZipUrl = artificialLocation() +
                File.separator +
                artificialLayer.getUrl();
        String uri = ZipUtil.extractAllBaseTarget(artificialLayerZipUrl, artificialLocation(), DEFAULT_FILE_PATTERN);
        if (null == uri) {
            return 0;
        }
        if (!FileUtil.delete(artificialLayerZipUrl)) {
            return 3;
        }
        // 设置真正的项目路径
        artificialLayer.setUrl(ARTIFICIAL_LAYER_URI + File.separator + uri);
        if (null == artificialLayer.getIsChecked()) {
            artificialLayer.setIsChecked(1);
        }
        int rows = artificialLayerMapper.insert(artificialLayer.getProjectId(),
                artificialLayer.getUrl(),
                artificialLayer.getName(),
                artificialLayer.getIsChecked(),
                artificialLayer.getDescription(),
                System.currentTimeMillis());
        return rows;
    }

    @Override
    public int remove(Long artificialLayerId) throws ArtificialLayerException {
        notNull(artificialLayerId);
        return artificialLayerMapper.delete(artificialLayerId, System.currentTimeMillis());
    }

    @Override
    public ArtificialLayer update(ArtificialLayer artificialLayer) throws ArtificialLayerException {
        notNull(artificialLayer);
        notNull(artificialLayer.getId());
        artificialLayer.setGmtModify(System.currentTimeMillis());
        artificialLayerMapper.update(artificialLayer);
        //todo 修改为修改后的数据
        return artificialLayer;
    }

    @Override
    public PageInfo<ArtificialLayer> list(PagedQueryDTO<ArtificialLayer> queryDTO) throws ArtificialLayerException {
        notNull(queryDTO);
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<ArtificialLayer> list = artificialLayerMapper.fetch(queryDTO.getQuery());
        return new PageInfo<ArtificialLayer>(list);
    }

    @Override
    public List<ArtificialLayer> listFromProjectForShow(Long projectId) {
        List<ArtificialLayer> list = artificialLayerMapper.fetchByProjectId(projectId);
        for (ArtificialLayer artificialLayer : list) {
            artificialLayer.setUrl(hostConfigService.packUrl(artificialLayer.getUrl()));
        }
        return list;
    }

    /**
     * 列出所有信息
     * @deprecated 历史遗留接口
     * @return
     */
    @Override
    @Deprecated
    public List<ArtificialLayer> listAll() {
        List<ArtificialLayer> list;
        list = artificialLayerMapper.fetchAll();
        return list;
    }
}
