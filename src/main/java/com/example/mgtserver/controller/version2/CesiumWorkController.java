package com.example.mgtserver.controller.version2;

import com.example.mgtserver.enums.ResponseCode;
import com.example.mgtserver.service.project.CesiumWorkService;
import com.example.mgtserver.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v2") // api_v2.js接收
public class CesiumWorkController {
    @Resource(name = "cesiumWorkService")
    private CesiumWorkService cesiumWorkService;

    /**
     * 获取 cesium 页面地址
     * @return cesium 页面的完整地址
     */
    @RequestMapping("/cesium/url")
    public ResultVO<String> cesiumPageUrl() {
        String url = cesiumWorkService.getCesiumPageUrl();
        return new ResultVO<>(ResponseCode.auto(url), url);
    }
}