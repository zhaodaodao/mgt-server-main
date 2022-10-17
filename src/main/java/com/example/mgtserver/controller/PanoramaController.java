package com.example.mgtserver.controller;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.enums.ResponseCode;
import com.example.mgtserver.exception.PanoramaException;
import com.example.mgtserver.model.KmlLayer;
import com.example.mgtserver.model.Panorama;
import com.example.mgtserver.service.layer.PanoramaService;
import com.example.mgtserver.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hexrt
 * @description
 * @create 2022-03-13 0:48
 */
@Controller
@ResponseBody
@RequestMapping("/api/panorama")
public class PanoramaController {
    @Resource(name = "panoramaService")
    private PanoramaService panoramaService;

    /**
     * 展示区域中所有的信息
     * @param queryDTO 分页条件查询
     * @return 模型列表
     */
    @PostMapping("/list")
    public ResultVO<PageInfo<Panorama>> list(@RequestBody PagedQueryDTO<Panorama> queryDTO) {
        return new ResultVO<>(ResponseCode.OK, panoramaService.list(queryDTO));
    }

    /**
     * 显示所有模型
     * @return 模型列表
     * @deprecated 历史遗留接口，不提供分页以及条件查询，查询所有未删除的全景图信息
     */
    @PostMapping("/list/all")
    @Deprecated
    public ResultVO<List<Panorama>> listAll() {
        return new ResultVO<>(ResponseCode.OK, panoramaService.listAll());
    }

    /**
     * 传输数据
     * @param file 多媒体文件
     * @return
     */
    @PostMapping("/upload/file")
    public ResultVO<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        String url = panoramaService.uploadFile(file);
        return  new ResultVO<>(ResponseCode.auto(url), url);
    }

    /**
     * 在上传文件成功后，更新项目信息
     * @param panorama 全景图信息
     * @return 上传结果
     */
    @PostMapping("/upload/info")
    public ResultVO<?> uploadInfo(@RequestBody Panorama panorama) throws Exception {
        int res = panoramaService.uploadInfo(panorama);
        return new ResultVO<>(ResponseCode.auto(res), null);
    }

    /**
     * 更新全景图信息
     * @param panorama 全景图
     * @return 更新后的信息
     * @throws Exception 多种错误
     */
    @PostMapping("/update")
    public ResultVO<Panorama> update(@RequestBody Panorama panorama) throws PanoramaException {
        panorama = panoramaService.update(panorama);
        return new ResultVO<Panorama>(ResponseCode.auto(panorama), panorama);
    }

    /**
     * 删除全景图信息
     * @param id 全景图id
     * @return 删除的id
     */
    @PostMapping("/remove")
    public ResultVO<Long> remove(@RequestParam("id") Long id) {
        int res = panoramaService.remove(id);
        return new ResultVO<>(ResponseCode.auto(res), id);
    }

    @PostMapping("/project")
    public ResultVO<List<Panorama>> listFromProjectForShow(@RequestParam("projectId") Long projectId) {
        List<Panorama> list = panoramaService.listFromProjectForShow(projectId);
        return new ResultVO<>(ResponseCode.auto(list), list);
    }
}
