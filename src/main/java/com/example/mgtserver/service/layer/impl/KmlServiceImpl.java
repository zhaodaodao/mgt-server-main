package com.example.mgtserver.service.layer.impl;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.exception.PanoramaException;
import com.example.mgtserver.mapper.KmlLayerMapper;
import com.example.mgtserver.model.ArtificialLayer;
import com.example.mgtserver.model.KmlLayer;
import com.example.mgtserver.model.Panorama;
import com.example.mgtserver.service.layer.KmlService;
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
import java.io.*;
import java.util.List;

@Service("kmlLayerService")
public class KmlServiceImpl implements KmlService {
    @Resource(name = "kmlLayerMapper")
    private KmlLayerMapper kmlLayerMapper;
    @Autowired
    private HostConfigService hostConfigService;

    @Value("${project-config.base-location}")
    private String BASE_LOCATION;

    @Value("${project-config.kml-uri}")
    private String KML_URI;


    /**
     * 默认接受文件类型
     */
    private final String DEFAULT_FILE_TYPE = ".zip";
    /**
     * 默认接受文件标识符文件名
     */
    private final String DEFAULT_FILE_PATTERN = ".kml";

    private String kmlLocation(){
        return (BASE_LOCATION + KML_URI).intern();
    }

    /**
     * 检查文件合法性
     * 检查基本信息，后缀为zip
     * @param file 传入的文件
     */
    private void validFile(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        if (suffix.trim().isEmpty() || !suffix.trim().equals(DEFAULT_FILE_TYPE)) {
            throw new PanoramaException("文件类型非法，需要类型：" + DEFAULT_FILE_TYPE + " 给定的为：" + suffix);
        }
    }

    /**
     *
     * @param file 预上传的kml压缩包
     */
    @Override
    public String upload(MultipartFile file) throws Exception {
        validFile(file);
        String url = MultipartFileUtil.upload(file, kmlLocation());
        if (null == url) {
            throw new PanoramaException("文件上传失败");
        }
        return url;
    }

    // todo
    @Override
    public int removeInProject(Long projectId) {
        return kmlLayerMapper.deleteAll(projectId, System.currentTimeMillis());
    }

    // todo
    @Override
    public List<KmlLayer> listInProject(Long projectId) {
        List<KmlLayer> list;
        list = kmlLayerMapper.fetchByProjectId(projectId);
        return list;
    }

    // todo
    @Override
    public List<KmlLayer> listAll() {
        List<KmlLayer> list;
        list = kmlLayerMapper.fetchAll();
        return list;
    }

    /**
     * 删除指定id的kml文件
     */
    @Override
    public int removeById(Long id) {
        return kmlLayerMapper.delete(id,System.currentTimeMillis());
    }

    /**
     * 更新kml信息
     */
    @Override
    public int update(KmlLayer kmlLayer) {
        return kmlLayerMapper.update(kmlLayer);
    }

    /**
     * 添加kml资源
     */
    @Override
    public int save(KmlLayer kmlLayer) throws Exception {
        String panoramaZipUrl = kmlLocation() + File.separator + kmlLayer.getUrl();
        String uri = ZipUtil.extractAllBaseTarget(panoramaZipUrl, kmlLocation(), DEFAULT_FILE_PATTERN);
        if (null == uri) {
            return 0;
        }
        if (!FileUtil.delete(panoramaZipUrl)) {
            return 3;
        }
        // 设置真正的项目路径
        kmlLayer.setUrl(KML_URI + File.separator + uri);
        return kmlLayerMapper.insert (kmlLayer.getProjectId(), kmlLayer.getUrl(), kmlLayer.getName(),kmlLayer.getIsChecked(),kmlLayer.getDescription(),System.currentTimeMillis());
    }

    /**
     * @param query 查询条件
     * @return 查询结果
     */
    @Override
    public PageInfo<KmlLayer> list(PagedQueryDTO<KmlLayer> query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<KmlLayer> list = kmlLayerMapper.fetch(query.getQuery());
        return new PageInfo<>(list);
    }

    @Override
    public List<KmlLayer> listFromProjectForShow(Long projectId) {
        List<KmlLayer> list = kmlLayerMapper.fetchByProjectId(projectId);
        for (KmlLayer kmlLayer : list) {
            kmlLayer.setUrl(hostConfigService.packUrl(kmlLayer.getUrl()));
        }
        return list;
    }
}
