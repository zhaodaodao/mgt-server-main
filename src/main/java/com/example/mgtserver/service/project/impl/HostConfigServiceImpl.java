package com.example.mgtserver.service.project.impl;

import com.example.mgtserver.mapper.HostConfigMapper;
import com.example.mgtserver.model.HostConfig;
import com.example.mgtserver.service.project.HostConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("hostConfigService")
public class HostConfigServiceImpl implements HostConfigService {
    @Resource
    private HostConfigMapper hostConfigMapper;

    @Value("${spring.profiles.active}")
    private String env;

    /**
     * 缓存时间
     */
    private static final Long CACHE_LIFE_CYCLE = 60 * 1000L;

    /**
     * 上次的同步时间
     */
    private Long gmtModify;

    /**
     * 装配好的配置对象
     */
    private HostConfig hostConfig;

    /**
     * 配置更新
     */
    private void update() {
        // 未被初始化（gmtModify为空）或 缓存失效 重新获取 config并更新gmtModify
        if (null == gmtModify || System.currentTimeMillis() > gmtModify + CACHE_LIFE_CYCLE) {
            hostConfig = hostConfigMapper.queryEnv(env);
            gmtModify = System.currentTimeMillis();
            assert hostConfig != null;
        }
    }

    @Override
    public String packUrl(String uri) {
        update();
        return "http://" + hostConfig.getHost() + ":" + hostConfig.getPort() + uri;
    }

    @Override
    public void updatePort(String port, String env) {
        hostConfigMapper.updatePort(port, env);
    }

    @Override
    public String unpackUrl(String url) {
        String tmp = url.replaceAll("http://", "");
        int index1 = tmp.indexOf("\\");
        int index2 = tmp.indexOf("/");
        // 两种分割符都存在，选择近的截取
        return getString(tmp, index1, index2);
    }

    @Override
    public String unpackUrl(String url, String target) {
        String target1 = target.replaceAll("[/]", "\\\\");
        int index1 = url.indexOf(target1);  // \target
        int index2 = url.indexOf(target);   // /target
        // 两种分割符都存在，选择近的截取
        return getString(url, index1, index2);
    }

    private String getString(String url, int index1, int index2) {
        if (index1 != -1 && index2 != -1) {
            return url.substring(Math.min(index1, index2));
        } else if (index1 != -1) {  // 只有第一种分隔符'\'
            return url.substring(index1);
        } else if (index2 != -1) {  // 只有第二种分隔符'/'
            return url.substring(index2);
        } else {
            return url;
        }
    }
}
