package com.example.mgtserver.controller;

import com.example.mgtserver.dto.layer.OsgbOfProjectDTO;
import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.exception.OsgbException;
import com.example.mgtserver.model.OsgbOfProject;
import com.example.mgtserver.model.Panorama;
import com.example.mgtserver.vo.ResultVO;
import com.example.mgtserver.enums.ResponseCode;
import com.example.mgtserver.model.Osgb;
import com.example.mgtserver.service.layer.OsgbService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hexrt
 * @description
 * @create 2022-03-13 0:58
 */
@Controller
@ResponseBody
@RequestMapping("/api/osgb/")
public class OsgbController {
    @Resource(name = "osgbService")
    private OsgbService osgbService;

    /**
     * 新增osgb模型
     *
     * @param osgb osgb模型信息
     * @return 响应结果
     */
    @PostMapping("/add")
    public ResultVO<Long> add(@RequestBody Osgb osgb) throws OsgbException {
        Long osgbId = osgbService.save(osgb);
        return new ResultVO<Long>(ResponseCode.auto(osgbId), osgbId);
    }

    /**
     * 移除osgb模型
     *
     * @param osgbId 模型id
     * @return 响应结果
     */
    @PostMapping("/remove")
    public ResultVO<?> remove(@RequestParam("id") Long osgbId) throws OsgbException {
        if (osgbService.remove(osgbId) == 1) {
            return new ResultVO<>(ResponseCode.OK);
        }
        return new ResultVO<>(ResponseCode.FAILED);
    }

    /**
     * 更新osgb信息
     * @param osgb osgb信息
     * @return
     * @throws OsgbException
     */
    @PostMapping("/update")
    public ResultVO<Osgb> update(@RequestBody Osgb osgb) throws OsgbException {
        osgb = osgbService.update(osgb);
        return new ResultVO<>(ResponseCode.auto(osgb), osgb);
    }

    /**
     * 条件查询osgb模型
     * @param queryDTO 查询数据
     * @return
     * @throws OsgbException
     */
    @PostMapping("/list")
    public ResultVO<PageInfo<Osgb>> list(@RequestBody PagedQueryDTO<Osgb> queryDTO) throws OsgbException {
        PageInfo<Osgb> res = osgbService.list(queryDTO);
        return new ResultVO<>(ResponseCode.auto(res), res);
    }

    /**
     * 显示所有数据
     * @deprecated 历史遗留接口
     * @return 响应结果
     */
    @Deprecated
    @PostMapping("/list/all")
    public ResultVO<List<Osgb>> listAll() {
        return new ResultVO<>(ResponseCode.OK, osgbService.listAll());
    }

    /**
     * 将osgb模型添加到项目Plus
     * 直接添加到项目
     *
     * @param osgbOfProjectDTO 模型关系
     * @return 响应结果
     */
    @PostMapping("/addToProject/plus")
    public ResultVO<?> addToProjectPlus(@RequestBody OsgbOfProjectDTO osgbOfProjectDTO) throws OsgbException {
        if (osgbService.addToProjectPlus(osgbOfProjectDTO) == 1) {
            return new ResultVO<>(ResponseCode.OK);
        } else {
            return new ResultVO<>(ResponseCode.FAILED);
        }
    }

    /**
     * 将osgb模型添加到项目
     *
     * @param osgbOfProject 模型关系
     * @return 响应结果
     */
    @PostMapping("/addToProject")
    public ResultVO<?> addToProject(@RequestBody OsgbOfProject osgbOfProject) throws OsgbException {
        if (osgbService.addToProject(osgbOfProject) == 1) {
            return new ResultVO<>(ResponseCode.OK);
        } else {
            return new ResultVO<>(ResponseCode.FAILED);
        }
    }

    /**
     * 删除项目中的osgb模型
     * @param osgbOfProjectDTO 关系模型
     * @return
     */
    @PostMapping("/removeFromProject")
    public ResultVO<?> removeFromProject(@RequestBody OsgbOfProjectDTO osgbOfProjectDTO) throws OsgbException {
        int res = osgbService.removeFromProject(osgbOfProjectDTO);
        return new ResultVO<>(ResponseCode.auto(res), res);
    }

    /**
     * 更新模型关系
     * @param osgbOfProjectDTO 模型关系信息
     * @return
     * @throws OsgbException
     */
    @PostMapping("/updateFromProject")
    public ResultVO<?> updateFromProject(@RequestBody OsgbOfProjectDTO osgbOfProjectDTO) throws OsgbException {
        Integer res = osgbService.updateFromProject(osgbOfProjectDTO);
        return new ResultVO<>(ResponseCode.auto(res), res);
    }

    /**
     * 显示项目内的模型
     *
     * @param queryDTO 分页查询
     * @return 模型列表
     */
    @PostMapping("/listFromProject")
    public ResultVO<PageInfo<OsgbOfProjectDTO>> listFromProject(@RequestBody PagedQueryDTO<OsgbOfProjectDTO> queryDTO) {
        return new ResultVO<>(ResponseCode.OK, osgbService.listFromProject(queryDTO));
    }

    @PostMapping("/project")
    public ResultVO<List<OsgbOfProjectDTO>> listFromProjectForShow(@RequestParam("projectId") Long projectId) {
        List<OsgbOfProjectDTO> list = osgbService.listFromProjectForShow(projectId);
        return new ResultVO<>(ResponseCode.auto(list), list);
    }
}
