package com.example.mgtserver.controller.version2;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.enums.ResponseCode;
import com.example.mgtserver.model.KmlLayer;
import com.example.mgtserver.model.version2.Osgb;
import com.example.mgtserver.service.version2.layer.OsgbService;
import com.example.mgtserver.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hexrt
 * @description osgb模型controller
 * @create 2022-04-10 21:37
 */
@Controller("osgbControllerV2")
@RequestMapping("/api/v2/osgb")
@ResponseBody
public class OsgbController {
    @Resource(name = "osgbServiceV2")
    private OsgbService osgbService;

    /**
     * 创建一个新的模型
     * @param osgb 模型数据
     * @return 创建结果
     */
    @PostMapping("/create")
    public ResultVO<?> create(@RequestBody Osgb osgb) {
        int code = osgbService.create(osgb);
        return  new ResultVO<>(ResponseCode.auto(code), null);
    }

    /**
     * 分页条件查询
     * @param queryDTO 查询体
     * @return 查询结果
     */
    @PostMapping("/list")
    public ResultVO<PageInfo<Osgb>> list(@RequestBody PagedQueryDTO<Osgb> queryDTO) {
        PageInfo<Osgb> pageInfo = osgbService.list(queryDTO);
        return new ResultVO<>(ResponseCode.auto(pageInfo), pageInfo);
    }

    /**
     * 通过项目id列出项目中所有模型
     * @param projectId 项目id
     * @return 项目中的模型
     */
    @PostMapping("/list/project")
    public ResultVO<List<Osgb>> listByProjectId(@RequestParam("projectId") Long projectId) {
        List<Osgb> list = osgbService.listByProjectId(projectId);
        return new ResultVO<>(ResponseCode.auto(list), list);
    }

    /**
     * 根据id移除模型
     * @param id 模型id
     * @return 移除结果
     */
    @PostMapping("/remove")
    public ResultVO<?> remove(@RequestParam("id") Long id) {
        int code = osgbService.remove(id);
        return new ResultVO<>(ResponseCode.auto(code), null);
    }

    /**
     * 批量删除
     * //todo
     * @deprecated 因为暂时未实现事务，所以不启用
     * @param ids id数组
     * @return 批量删除结果
     */
    @Deprecated
    @PostMapping("/remove/batch")
    public ResultVO<?> removeBatch(@RequestParam("ids") Long[] ids) {
        return new ResultVO<>(ResponseCode.FAILED, null);
    }

    /**
     * 更新模型信息
     * @param osgb 模型数据
     * @return 更新结果
     */
    @PostMapping("/update")
    public ResultVO<Osgb> update(@RequestBody Osgb osgb) {
        osgb = osgbService.update(osgb);
        return new ResultVO<>(ResponseCode.auto(osgb), osgb);
    }

    /**
     * 更新模型默认选中属性
     * @param id        模型id
     * @param isChecked 是否默认选中
     * @return 更新结果
     */
    @PostMapping("/update/checked")
    public ResultVO<?> updateChecked(@RequestParam("id") Long id, @RequestParam("isChecked") Integer isChecked) {
        int code = osgbService.updateChecked(id, isChecked);
        return new ResultVO<>(ResponseCode.auto(code), null);
    }
}
