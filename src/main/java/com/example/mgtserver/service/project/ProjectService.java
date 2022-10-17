package com.example.mgtserver.service.project;

import com.example.mgtserver.dto.project.ProjectStat;
import com.example.mgtserver.model.Project;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 项目业务接口
 *
 * @author Hexrt
 */
public interface ProjectService {
    /**
     * 新建项目
     *
     * @param newProject 新的项目
     * @return 状态 0 失败，1 成功
     */
    int createNewProject(Project newProject);

    /**
     * 移除项目
     *
     * @param id 目标项目Id
     * @return 状态 0 失败，1 成功
     */
    int removeProject(Long id);

    /**
     * 修改项目信息
     *
     * @param project 新的项目信息对象
     * @return 状态 0 失败，1 成功
     */
    int modifyProject(Project project);

    /**
     * 列出所有项目
     *
     * @return 项目列表
     */
    List<Project> listAll();

    /**
     * 获取项目统计信息
     *
     * @param id 项目id
     * @return 项目统计信息对象
     */
    ProjectStat getProjectStat(Long id);

    /**
     * 上传图片
     * @param img 上传的图片文件
     * @return 上传后图片的URL
     * @throws IOException 文件系统异常
     */
    String uploadImage(MultipartFile img) throws IOException;
}
