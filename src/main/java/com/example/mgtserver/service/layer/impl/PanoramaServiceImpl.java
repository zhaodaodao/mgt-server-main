package com.example.mgtserver.service.layer.impl;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.exception.PanoramaException;
import com.example.mgtserver.mapper.PanoramaMapper;
import com.example.mgtserver.model.KmlLayer;
import com.example.mgtserver.model.Panorama;
import com.example.mgtserver.service.layer.PanoramaService;
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
import javax.xml.transform.Source;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author Hexrt
 * @description 全景图业务
 * @create 2022-04-03 12:40
 */
@Service("panoramaService")
public class PanoramaServiceImpl implements PanoramaService {
    @Resource(name = "panoramaMapper")
    private PanoramaMapper panoramaMapper;
    @Autowired
    private HostConfigService hostConfigService;

    @Value("${project-config.base-location}")
    private String BASE_LOCATION;

    @Value("${project-config.panorama-uri}")
    private String PANORAMA_URI;

    /**
     * 默认接受文件类型
     */
    private final String DEFAULT_FILE_TYPE = ".zip";
    /**
     * 默认接受文件标识符文件名
     */
    private final String DEFAULT_FILE_PATTERN = "index.html";

    /**
     * 获取当前项目的全景图存储路径
     * @return 存储路径
     */
    private String panoramaLocation() {
        return (BASE_LOCATION + PANORAMA_URI).intern();
    }

    /**
     * 检查文件合法性
     * 检查基本信息，后缀为zip
     * @param file 传入的文件
     */
    private void validFile(MultipartFile file) throws PanoramaException {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        if (suffix.trim().isEmpty() || !suffix.trim().equals(DEFAULT_FILE_TYPE)) {
            throw new PanoramaException("文件类型非法，需要类型：" + DEFAULT_FILE_TYPE + " 给定的为：" + suffix);
        }
    }

    /**
     * 检验基本参数
     * @param panorama 全景图
     * @throws PanoramaException
     */
    private void validParamBase(Panorama panorama) throws PanoramaException {
        if (null != panorama.getId()) {
            if (panorama.getId() <= 0) {
                throw new PanoramaException("id不合法 id:" + panorama.getId());
            }
        }
        if (null != panorama.getLongitude()) {
            try {
                Double.parseDouble(panorama.getLongitude());
            } catch (NumberFormatException e) {
                throw new PanoramaException("longitude格式错误 " + panorama.getLongitude());
            }
        }
        if (null != panorama.getLatitude()) {
            try {
                Double.parseDouble(panorama.getLatitude());
            } catch (NumberFormatException e) {
                throw new PanoramaException("latitude格式错误 " + panorama.getLatitude());
            }
        }
        if (null != panorama.getAltitude()) {
            try {
                Double.parseDouble(panorama.getAltitude());
            } catch (NumberFormatException e) {
                throw new PanoramaException("altitude格式错误 " + panorama.getAltitude());
            }
        }
    }

    /**
     * 校验参数合法性，更新使用
     * @param panorama  全景图信息
     */
    private void validParamForUpdate(Panorama panorama) throws PanoramaException {
        validParamBase(panorama);
        if (null == panorama.getId()) {
            throw new PanoramaException("未提供id进行更新");
        }
        // 禁止更新事项
        panorama.setUrl(null);
        panorama.setGmtCreate(null);
    }

    /**
     * 检验参数合法
     * @param panorama 传入的全景图信息
     */
    private void validParamForUpload(Panorama panorama) throws PanoramaException {
        if (panorama.getProjectId() == null || panorama.getName() == null) {
            throw new PanoramaException("关键信息未给出");
        }
        if (panorama.getLongitude() == null || panorama.getLatitude() == null || panorama.getAltitude() == null) {
            throw new PanoramaException("经纬度未给出");
        }
        if (panorama.getUrl() == null || !FileUtil.exist(panoramaLocation() + File.separator + panorama.getUrl())) {
            //todo 检验是否有此文件
            throw new PanoramaException("预上传文件未给定");
        }
        validParamBase(panorama);
    }

    /**
     * 上传文件
     *
     * @param file   全景图压缩文件
     * @return 上传文件
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        //todo 检查文件合法性
        validFile(file);
        String url = MultipartFileUtil.upload(file, panoramaLocation());
        if (null == url) {
            throw new PanoramaException("文件上传失败");
        }
        return url;
    }

    /**
     * 上传信息
     * 此时将会解压之前上传的文件压缩包
     * @param panorama  全景图信息
     * @return 上传结果
     */
    @Override
    public int uploadInfo(Panorama panorama) throws Exception {
        //todo 检查参数合法性
        validParamForUpload(panorama);
        String panoramaZipUrl = panoramaLocation() + File.separator + panorama.getUrl();
        String uri = ZipUtil.extractAllBaseTarget(panoramaZipUrl, panoramaLocation(), DEFAULT_FILE_PATTERN);
        if (null == uri) {
            return 0;
        }
        if (!FileUtil.delete(panoramaZipUrl)) {
            return 3;
        }
        // 设置真正的项目路径
        panorama.setUrl(PANORAMA_URI + File.separator + uri);
        int rows = panoramaMapper.insert(panorama.getProjectId(), panorama.getUrl(), panorama.getName(), panorama.getLongitude(), panorama.getLatitude(), panorama.getAltitude(), panorama.getIsChecked(), System.currentTimeMillis());
        return rows;
    }

    /**
     * 删除
     *
     * @param id 全景图id
     * @return
     */
    @Override
    public int remove(Long id) {
        return panoramaMapper.delete(id, System.currentTimeMillis());
    }

    /**
     * 显示所有区域中的模型
     *
     * @param projectId 项目Id
     * @return 模型列表
     */
    @Override
    public List<Panorama> listInProject(Long projectId) {
        return panoramaMapper.fetchByProjectId(projectId);
    }

    /**
     * 显示所有模型
     *
     * @return 模型列表
     */
    @Override
    public List<Panorama> listAll() {
        return panoramaMapper.fetchAll();
    }

    /**
     * 条件分页查询
     * @param query 查询体
     * @return 分页查询结果
     */
    @Override
    public PageInfo<Panorama> list(PagedQueryDTO<Panorama> query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<Panorama> list = panoramaMapper.fetch(query.getQuery());
        return new PageInfo<>(list);
    }

    /**
     * 更新全景图信息
     * @param panorama 全景图信息
     * @return 修改后的全景图信息
     */
    @Override
    public Panorama update(Panorama panorama) throws PanoramaException {
        validParamForUpdate(panorama);
        panorama.setGmtModify(System.currentTimeMillis());
        panoramaMapper.update(panorama);
        return panoramaMapper.fetchById(panorama.getId());
    }

    @Override
    public List<Panorama> listFromProjectForShow(Long projectId) {
        List<Panorama> list = panoramaMapper.fetchByProjectId(projectId);
        for (Panorama panorama : list) {
            panorama.setUrl(hostConfigService.packUrl(panorama.getUrl()));
        }
        return list;
    }
}
