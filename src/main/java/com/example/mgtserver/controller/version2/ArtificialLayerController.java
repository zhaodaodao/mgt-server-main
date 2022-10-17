package com.example.mgtserver.controller.version2;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.enums.ResponseCode;
import com.example.mgtserver.model.ArtificialLayer;
import com.example.mgtserver.service.version2.layer.ArtificialLayerService;
import com.example.mgtserver.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hexrt
 * @description 人工模型Controller V2.0
 * @create 2022-04-08 19:21
 */
@Controller("artificialControllerV2")
@RequestMapping("/api/v2/artificial")
@ResponseBody
public class ArtificialLayerController {
    @Resource(name = "artificialLayerServiceV2")
    private ArtificialLayerService artificialLayerService;

    /**
     * 创建一个新的模型
     * @param artificialLayer 模型数据
     * @return 创建结果
     */
    @PostMapping("/create")
    public ResultVO<?> create(@RequestBody ArtificialLayer artificialLayer) {
        int code = artificialLayerService.create(artificialLayer);
        return  new ResultVO<>(ResponseCode.auto(code), null);
    }

    /**
     * 分页条件查询
     * @param queryDTO 查询体
     * @return 查询结果
     */
    @PostMapping("/list")
    public ResultVO<PageInfo<ArtificialLayer>> list(@RequestBody PagedQueryDTO<ArtificialLayer> queryDTO) {
        PageInfo<ArtificialLayer> pageInfo = artificialLayerService.list(queryDTO);
        return new ResultVO<>(ResponseCode.auto(pageInfo), pageInfo);
    }

    /**
     * 通过项目id列出项目中所有模型
     * @param projectId 项目id
     * @return 项目中的模型
     */
    @PostMapping("/list/project")
    public ResultVO<List<ArtificialLayer>> listByProjectId(@RequestParam("projectId") Long projectId) {
        List<ArtificialLayer> list = artificialLayerService.listByProjectId(projectId);
        return new ResultVO<>(ResponseCode.auto(list), list);
    }

    /**
     * 根据id移除模型
     * @param id 模型id
     * @return 移除结果
     */
    @PostMapping("/remove")
    public ResultVO<?> remove(@RequestParam("id") Long id) {
        int code = artificialLayerService.remove(id);
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
     * @param artificialLayer 模型数据
     * @return 更新结果
     */
    @PostMapping("/update")
    public ResultVO<ArtificialLayer> update(@RequestBody ArtificialLayer artificialLayer) {
        artificialLayer = artificialLayerService.update(artificialLayer);
        return new ResultVO<>(ResponseCode.auto(artificialLayer), artificialLayer);
    }

    /**
     * 更新模型默认选中属性
     * @param id        模型id
     * @param isChecked 是否默认选中
     * @return 更新结果
     */
    @PostMapping("/update/checked")
    public ResultVO<?> updateChecked(@RequestParam("id") Long id, @RequestParam("isChecked") Integer isChecked) {
        int code = artificialLayerService.updateChecked(id, isChecked);
        return new ResultVO<>(ResponseCode.auto(code), null);
    }
}
