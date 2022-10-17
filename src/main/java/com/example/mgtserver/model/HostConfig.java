package com.example.mgtserver.model;

public class HostConfig {
    /**
     * 环境区分
     */
    private String env;
    /**
     * 主机
     */
    private String host;
    /**
     * 端口号
     */
    private Integer port;

    public HostConfig() {
    }

    public HostConfig(String env, String host, Integer port) {
        this.env = env;
        this.host = host;
        this.port = port;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
