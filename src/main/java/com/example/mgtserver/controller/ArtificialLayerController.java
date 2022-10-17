package com.example.mgtserver.controller;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.enums.ResponseCode;
import com.example.mgtserver.exception.ArtificialLayerException;
import com.example.mgtserver.exception.PanoramaException;
import com.example.mgtserver.model.ArtificialLayer;
import com.example.mgtserver.model.Panorama;
import com.example.mgtserver.service.layer.ArtificialLayerService;
import com.example.mgtserver.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hexrt
 */
@Controller
@RequestMapping("/api/artificial")
@ResponseBody
public class ArtificialLayerController {
    @Resource(name = "artificialLayerService")
    private ArtificialLayerService artificialLayerService;

    /**
     *
     * @deprecated 历史遗留接口
     * @return
     */
    @PostMapping("/list/all")
    @Deprecated
    public ResultVO<List<ArtificialLayer>> listAll() {
        List<ArtificialLayer> list = artificialLayerService.listAll();
        if (list == null) {
            return new ResultVO<>(ResponseCode.OK, null);
        } else {
            return new ResultVO<>(ResponseCode.OK, list);
        }
    }

    /**
     * 传输数据
     * @param file 多媒体文件
     * @return
     */
    @PostMapping("/upload/file")
    public ResultVO<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        String url = artificialLayerService.uploadFile(file);
        return  new ResultVO<>(ResponseCode.auto(url), url);
    }

    /**
     * 在上传文件成功后，更新项目信息
     * @param artificialLayer 人工模型数据
     * @return 上传结果
     */
    @PostMapping("/upload/info")
    public ResultVO<Integer> uploadInfo(@RequestBody ArtificialLayer artificialLayer) throws Exception {
        int res = artificialLayerService.uploadInfo(artificialLayer);
        return new ResultVO<>(ResponseCode.auto(res), res);
    }

    /**
     * 删除全景图信息
     * @param id 全景图id
     * @return 删除的id
     */
    @PostMapping("/remove")
    public ResultVO<Long> remove(@RequestParam("id") Long id) throws ArtificialLayerException {
        int res = artificialLayerService.remove(id);
        return new ResultVO<>(ResponseCode.auto(res), id);
    }

    /**
     * 更新人工模型信息
     * @param artificialLayer 人工模型
     * @return 更新后的信息
     * @throws Exception 多种错误
     */
    @PostMapping("/update")
    public ResultVO<ArtificialLayer> update(@RequestBody ArtificialLayer artificialLayer) throws Exception {
        artificialLayer = artificialLayerService.update(artificialLayer);
        return new ResultVO<ArtificialLayer>(ResponseCode.auto(artificialLayer), artificialLayer);
    }

    /**
     * 条件分页查询
     * @param queryDTO 查询
     * @return
     * @throws ArtificialLayerException
     */
    @PostMapping("/list")
    public ResultVO<PageInfo<ArtificialLayer>> list(@RequestBody PagedQueryDTO<ArtificialLayer> queryDTO) throws ArtificialLayerException {
        PageInfo<ArtificialLayer> layerPageInfo = artificialLayerService.list(queryDTO);
        return new ResultVO<>(ResponseCode.auto(layerPageInfo), layerPageInfo);
    }

    @PostMapping("/project")
    public ResultVO<List<ArtificialLayer>> listFromProjectForShow(@RequestParam("projectId") Long projectId) {
        List<ArtificialLayer> list = artificialLayerService.listFromProjectForShow(projectId);
        return new ResultVO<>(ResponseCode.auto(list), list);
    }
}
