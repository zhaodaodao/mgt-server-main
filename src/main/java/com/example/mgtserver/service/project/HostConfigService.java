package com.example.mgtserver.service.project;

/**
 * 资源服务主机配置业务
 */
public interface HostConfigService {
    /**
     * 组装url
     *
     * @param uri 待包装路径
     * @return 包装完成的完整路径
     */
    String packUrl(String uri);

    /**
     * 更新端口号
     *
     * @param port 新的端口号
     * @param env  目标环境
     */
    void updatePort(String port, String env);

    /**
     * 拆分url
     *
     * @param url 目标url
     * @return 拆分后的uri
     */
    String unpackUrl(String url);

    /**
     * 根据解析目标拆解url
     * 将解析至target之后的位置
     *
     * @param url 目标url
     * @param target 提供的解析目标
     * @return 拆分后的uri
     */
    String unpackUrl(String url, String target);
}
