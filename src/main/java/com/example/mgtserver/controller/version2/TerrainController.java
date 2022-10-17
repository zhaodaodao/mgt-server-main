package com.example.mgtserver.controller.version2;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.enums.ResponseCode;
import com.example.mgtserver.model.version2.Terrain;
import com.example.mgtserver.service.version2.layer.TerrainService;
import com.example.mgtserver.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller("terrainController")
@RequestMapping("/api/v2/terrain")
@ResponseBody
public class TerrainController {
    @Resource(name = "terrainService")
    private TerrainService terrainService;

    /**
     * 创建一个新的地形数据模型
     * @param terrain 地形数据模型
     * @return 创建结果
     */
    @PostMapping("/create")
    public ResultVO<?> create(@RequestBody com.example.mgtserver.model.version2.Terrain terrain) {
        int code = terrainService.create(terrain);
        return  new ResultVO<>(ResponseCode.auto(code), null);
    }

    /**
     * 分页条件查询
     * @param queryDTO 查询体
     * @return 查询结果
     */
    @PostMapping("/list")
    public ResultVO<PageInfo<Terrain>> list(@RequestBody PagedQueryDTO<Terrain> queryDTO) {
        PageInfo<Terrain> pageInfo = terrainService.list(queryDTO);
        return new ResultVO<>(ResponseCode.auto(pageInfo), pageInfo);
    }

    /**
     * 根据id移除模型
     * @param id 模型id
     * @return 移除结果
     */
    @PostMapping("/remove")
    public ResultVO<?> remove(@RequestParam("id") Long id) {
        int code = terrainService.remove(id);
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
     * @param terrain 模型数据
     * @return 更新结果
     */
    @PostMapping("/update")
    public ResultVO<Terrain> update(@RequestBody Terrain terrain) {
        terrain = terrainService.update(terrain);
        return new ResultVO<>(ResponseCode.auto(terrain), terrain);
    }

    /**
     * 更新模型默认选中属性
     * @param id        模型id
     * @param isChecked 是否默认选中
     * @return 更新结果
     */
    @PostMapping("/update/checked")
    public ResultVO<?> updateChecked(@RequestParam("id") Long id, @RequestParam("isChecked") Integer isChecked) {
        int code = terrainService.updateChecked(id, isChecked);
        return new ResultVO<>(ResponseCode.auto(code), null);
    }

    /**
     * 根据项目列出所有地形数据
     * @param projectId 项目id
     * @return 查询结果
     */
    @PostMapping("/list/project")
    public ResultVO<?> listByProjectId(@RequestParam("projectId") Long projectId) {
        List<Terrain> tiffList = terrainService.listByProjectId(projectId);
        return new ResultVO<>(ResponseCode.auto(tiffList), tiffList);
    }

    @PostMapping("/update/associatedOsgb")
    public ResultVO<?> updateAssociatedOsgb(@RequestBody Terrain terrain) {
        return new ResultVO<>(ResponseCode.auto(terrainService.updateAssociatedOsgb(terrain)));
    }

    @PostMapping("/dissociateOsgb")
    public ResultVO<?> dissociateOsgb(@RequestParam Long osgbId) {
        return new ResultVO<>(ResponseCode.auto(terrainService.dissociateOsgb(osgbId)));
    }
}
