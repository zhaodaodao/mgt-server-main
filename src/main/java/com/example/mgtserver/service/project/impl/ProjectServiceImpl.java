package com.example.mgtserver.service.project.impl;

import com.example.mgtserver.dto.project.ProjectStat;
import com.example.mgtserver.mapper.ProjectMapper;
import com.example.mgtserver.model.Project;
import com.example.mgtserver.service.project.HostConfigService;
import com.example.mgtserver.service.project.ProjectService;
import com.example.mgtserver.utils.MultipartFileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author Hexrt
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    @Resource(name = "projectMapper")
    private ProjectMapper projectMapper;

    @Resource
    private HostConfigService hostConfigService;

    @Value("${project-config.base-location}")
    private String BASE_LOCATION;

    @Value("${project-config.project-image-uri}")
    private String IMG_URI;



    @Override
    public int createNewProject(@RequestParam Project newProject) {
        // 验证字段合法性
        if (newProject.getGmtStart() == null) {
            return 0;
        }

        // 对imgUrl拆解
        if (newProject.getImgUrl() != null) {
            newProject.setImgUrl(hostConfigService.unpackUrl(newProject.getImgUrl()));
        }

        newProject.setGmtCreate(System.currentTimeMillis());
        newProject.setGmtModify(System.currentTimeMillis());
        return projectMapper.insert(newProject);
    }

    @Override
    public int removeProject(Long id) {
        if (null == id) {
            return 0;
        }
        //todo 对是否存在数据等，数据是否可见(is_deleted)的检验

        return projectMapper.delete(id, System.currentTimeMillis());
    }

    @Override
    public int modifyProject(Project project) {
        if (null == project) {
            return 0;
        }
        //todo 检验

        return projectMapper.update(project.getId(), project.getName(), System.currentTimeMillis());
    }

    @Override
    public List<Project> listAll() {
        List<Project> list = projectMapper.fetchAll();
        if (list != null) {
            // 将url组装
            for(Project project : list) {
                if (project.getImgUrl() != null) {
                    project.setImgUrl(hostConfigService.packUrl(project.getImgUrl()));
                }
            }
            return list;
        }
        return null;
    }

    @Override
    public ProjectStat getProjectStat(Long id) {
        return projectMapper.queryProjectStatistic(id);
    }

    @Override
    public String uploadImage(MultipartFile img) throws IOException {
        if (img == null) {
            return null;
        } else {
            return hostConfigService.packUrl(IMG_URI + "/" + MultipartFileUtil.upload(img, imgLocation()));
        }
    }

    private String imgLocation() {
        return BASE_LOCATION + IMG_URI;
    }
}
