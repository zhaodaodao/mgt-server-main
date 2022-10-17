package com.example.mgtserver.controller;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.enums.ResponseCode;
import com.example.mgtserver.model.ArtificialLayer;
import com.example.mgtserver.model.KmlLayer;
import com.example.mgtserver.model.Panorama;
import com.example.mgtserver.service.layer.KmlService;
import com.example.mgtserver.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/api/kml")
@ResponseBody
public class KmlLayerController {
    @Resource(name = "kmlLayerService")
    private KmlService kmlService;
    @PostMapping("/list/all")
    public ResultVO<List<KmlLayer>> listAll() {
        List<KmlLayer>list = kmlService.listAll();
        if (list == null){
            return new ResultVO<>(ResponseCode.OK, null);
        }else{
            return new ResultVO<>(ResponseCode.OK, list);
        }
    }

    @PostMapping("/list")
    public ResultVO<PageInfo<KmlLayer>> listByProjectId(@RequestBody PagedQueryDTO<KmlLayer> query){
        return new ResultVO<>(ResponseCode.OK, kmlService.list(query));
    }

    /**
     * @param file 预上传kml压缩包
     */
    @PostMapping("/upload")
    public ResultVO<?> upload(@RequestParam MultipartFile file) throws Exception {
        String url = kmlService.upload(file);
        return  new ResultVO<>(ResponseCode.auto(url), url);
    }

    /**
     * 在上传文件成功后，表单提交后更新项目信息
     * @param kmlLayer kml文件相关信息
     * @return 上传结果
     */
    @PostMapping("/save")
    public ResultVO<?> uploadInfo(@RequestBody KmlLayer kmlLayer) throws Exception {
        return new ResultVO<>(ResponseCode.auto(kmlService.save(kmlLayer)), null);
    }

    @RequestMapping("/delete")
    public ResultVO<?> deleteById(Long id){
       if (kmlService.removeById(id) == 1){
           return new ResultVO<>(ResponseCode.OK,null);
       }else{
           return new ResultVO<>(ResponseCode.FAILED,null);
       }
    }

    @PostMapping("/update")
    public ResultVO<?> update(@RequestBody KmlLayer kmlLayer){
        kmlService.update(kmlLayer);
        return new ResultVO<>(ResponseCode.auto(kmlLayer), null);
    }

    @PostMapping("/project")
    public ResultVO<List<KmlLayer>> listFromProjectForShow(@RequestParam("projectId") Long projectId) {
        List<KmlLayer> list = kmlService.listFromProjectForShow(projectId);
        return new ResultVO<>(ResponseCode.auto(list), list);
    }
}
