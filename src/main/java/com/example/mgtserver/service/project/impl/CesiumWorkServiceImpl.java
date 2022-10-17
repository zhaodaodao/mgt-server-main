package com.example.mgtserver.service.project.impl;

import com.example.mgtserver.service.project.CesiumWorkService;
import com.example.mgtserver.service.project.HostConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("cesiumWorkService")
public class CesiumWorkServiceImpl implements CesiumWorkService {

    @Resource(name = "hostConfigService")
    private HostConfigService hostConfigService;

    @Override
    public String getCesiumPageUrl() {
        // 将 cesium 应用名包装成完整url
        final String cesiumAppUri = "/vue3-cesium";
        return hostConfigService.packUrl(cesiumAppUri);
    }
}
