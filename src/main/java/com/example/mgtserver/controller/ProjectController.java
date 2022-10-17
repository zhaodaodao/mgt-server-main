package com.example.mgtserver.controller;

import com.example.mgtserver.dto.project.ProjectStat;
import com.example.mgtserver.vo.ResultVO;
import com.example.mgtserver.enums.ResponseCode;
import com.example.mgtserver.model.Project;
import com.example.mgtserver.service.project.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author Hexrt
 * @description 项目管理
 * @create 2022-03-12 15:47
 */
@Controller
@ResponseBody
@RequestMapping("/api/project")
public class ProjectController {
    @Resource(name = "projectService")
    public ProjectService projectService;

    /**
     * 显示所有项目
     *
     * @return 项目列表
     */
    @PostMapping("/list")
    public ResultVO<List<Project>> list() {
        List<Project> list = projectService.listAll();
        return new ResultVO<>(ResponseCode.OK, list);
    }

    /**
     * 创建新项目
     *
     * @param newProject 新的项目
     * @return 创建结果
     */
    @PostMapping("/create")
    public ResultVO<?> create(@RequestBody Project newProject) {
        int resCode = projectService.createNewProject(newProject);
        if (1 == resCode) {
            return new ResultVO<>(ResponseCode.OK, null);
        } else {
            return new ResultVO<>(ResponseCode.FAILED, null);
        }
    }

    @PostMapping("/uploadImage")
    public ResultVO<?> uploadProjectImage(@RequestParam("img") MultipartFile img) throws IOException {
        String url = projectService.uploadImage(img);
        if (url == null) {
            return new ResultVO<>(ResponseCode.FAILED);
        } else {
            return new ResultVO<>(ResponseCode.OK, url);
        }
    }

    /**
     * 删除项目
     *
     * @param id 项目id
     * @return 删除结果
     */
    @PostMapping("/delete")
    public ResultVO<?> delete(@RequestParam("id") Long id) {
        int resCode = projectService.removeProject(id);
        if (1 == resCode) {
            return new ResultVO<>(ResponseCode.OK, null);
        } else {
            return new ResultVO<>(ResponseCode.FAILED, null);
        }
    }

    /**
     * 修改项目
     *
     * @param project 项目信息
     * @return 修改结果
     */
    @PostMapping("/update")
    public ResultVO<?> modify(@RequestBody Project project) {
        int resCode = projectService.modifyProject(project);
        if (1 == resCode) {
            return new ResultVO<>(ResponseCode.OK, null);
        } else {
            return new ResultVO<>(ResponseCode.FAILED, null);
        }
    }

    /**
     * 项目信息统计接口
     *
     * @param id 目标项目id
     * @return 响应结果
     */
    @PostMapping("/stat")
    public ResultVO<ProjectStat> stat(@RequestParam("id") Long id) {
        return new ResultVO<>(ResponseCode.OK, projectService.getProjectStat(id));
    }


}
