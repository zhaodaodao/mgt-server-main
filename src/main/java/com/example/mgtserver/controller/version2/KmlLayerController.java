package com.example.mgtserver.controller.version2;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.enums.ResponseCode;
import com.example.mgtserver.model.ArtificialLayer;
import com.example.mgtserver.model.KmlLayer;
import com.example.mgtserver.service.version2.layer.KmlLayerService;
import com.example.mgtserver.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hexrt
 * @description kml模型Controller
 * @create 2022-04-10 21:37
 */
@Controller("kmlLayerControllerV2")
@RequestMapping("/api/v2/kml")
@ResponseBody
public class KmlLayerController {
    @Resource(name = "kmlLayerServiceV2")
    private KmlLayerService kmlLayerService;

    /**
     * 创建一个新的模型
     * @param kmlLayer 模型数据
     * @return 创建结果
     */
    @PostMapping("/create")
    public ResultVO<?> create(@RequestBody KmlLayer kmlLayer) {
        int code = kmlLayerService.create(kmlLayer);
        return  new ResultVO<>(ResponseCode.auto(code), null);
    }

    /**
     * 分页条件查询
     * @param queryDTO 查询体
     * @return 查询结果
     */
    @PostMapping("/list")
    public ResultVO<PageInfo<KmlLayer>> list(@RequestBody PagedQueryDTO<KmlLayer> queryDTO) {
        PageInfo<KmlLayer> pageInfo = kmlLayerService.list(queryDTO);
        return new ResultVO<>(ResponseCode.auto(pageInfo), pageInfo);
    }

    /**
     * 通过项目id列出项目中所有模型
     * @param projectId 项目id
     * @return 项目中的模型
     */
    @PostMapping("/list/project")
    public ResultVO<List<KmlLayer>> listByProjectId(@RequestParam("projectId") Long projectId) {
        List<KmlLayer> list = kmlLayerService.listByProjectId(projectId);
        return new ResultVO<>(ResponseCode.auto(list), list);
    }

    /**
     * 根据id移除模型
     * @param id 模型id
     * @return 移除结果
     */
    @PostMapping("/remove")
    public ResultVO<?> remove(@RequestParam("id") Long id) {
        int code = kmlLayerService.remove(id);
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
     * @param kmlLayer 模型数据
     * @return 更新结果
     */
    @PostMapping("/update")
    public ResultVO<KmlLayer> update(@RequestBody KmlLayer kmlLayer) {
        kmlLayer = kmlLayerService.update(kmlLayer);
        return new ResultVO<>(ResponseCode.auto(kmlLayer), kmlLayer);
    }

    /**
     * 更新模型默认选中属性
     * @param id        模型id
     * @param isChecked 是否默认选中
     * @return 更新结果
     */
    @PostMapping("/update/checked")
    public ResultVO<?> updateChecked(@RequestParam("id") Long id, @RequestParam("isChecked") Integer isChecked) {
        int code = kmlLayerService.updateChecked(id, isChecked);
        return new ResultVO<>(ResponseCode.auto(code), null);
    }
}
