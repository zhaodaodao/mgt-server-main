package com.example.mgtserver.controller.version2;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.enums.ResponseCode;
import com.example.mgtserver.model.version2.Tiff;
import com.example.mgtserver.service.version2.layer.TiffService;
import com.example.mgtserver.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 卫星影像数据控制层
 */
@Controller("tiffController")
@RequestMapping("/api/v2/tiff")
@ResponseBody
public class TiffController {
    @Resource(name = "tiffService")
    private TiffService tiffService;

    /**
     * 创建一个新的卫星影像数据模型
     * @param tiff 卫星影像数据模型
     * @return 创建结果
     */
    @PostMapping("/create")
    public ResultVO<?> create(@RequestBody Tiff tiff) {
        int code = tiffService.create(tiff);
        return  new ResultVO<>(ResponseCode.auto(code), null);
    }

    /**
     * 分页条件查询
     * @param queryDTO 查询体
     * @return 查询结果
     */
    @PostMapping("/list")
    public ResultVO<PageInfo<Tiff>> list(@RequestBody PagedQueryDTO<Tiff> queryDTO) {
        PageInfo<Tiff> pageInfo = tiffService.list(queryDTO);
        return new ResultVO<>(ResponseCode.auto(pageInfo), pageInfo);
    }

    /**
     * 根据id卫星影像数据模型
     * @param id 卫星影像数据模型id
     * @return 移除结果
     */
    @PostMapping("/remove")
    public ResultVO<?> remove(@RequestParam("id") Long id) {
        int code = tiffService.remove(id);
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
     * @param tiff 模型数据
     * @return 更新结果
     */
    @PostMapping("/update")
    public ResultVO<Tiff> update(@RequestBody Tiff tiff) {
        tiff = tiffService.update(tiff);
        return new ResultVO<>(ResponseCode.auto(tiff), tiff);
    }

    /**
     * 更新模型默认选中属性
     * @param id        模型id
     * @param isChecked 是否默认选中
     * @return 更新结果
     */
    @PostMapping("/update/checked")
    public ResultVO<?> updateChecked(@RequestParam("id") Long id, @RequestParam("isChecked") Integer isChecked) {
        int code = tiffService.updateChecked(id, isChecked);
        return new ResultVO<>(ResponseCode.auto(code), null);
    }

    /**
     * 根据项目列出所有卫星影像数据
     * @param projectId 项目id
     * @return 查询结果
     */
    @PostMapping("/list/project")
    public ResultVO<?> listByProjectId(@RequestParam("projectId") Long projectId) {
        List<Tiff> tiffList = tiffService.listByProjectId(projectId);
        return new ResultVO<>(ResponseCode.auto(tiffList), tiffList);
    }
}
