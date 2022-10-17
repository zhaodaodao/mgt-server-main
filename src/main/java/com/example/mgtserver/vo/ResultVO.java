package com.example.mgtserver.vo;

import com.example.mgtserver.enums.ResponseCode;

/**
 * @author Hexrt
 * @description 返回体
 * @create 2022-03-11 20:26
 */
public class ResultVO<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    public ResultVO(ResponseCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    /**
     * 返回体
     *
     * @param code    状态码
     * @param message 状态信息
     * @param data    返回数据
     */
    public ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回体
     *
     * @param code 状态码枚举
     * @param data 返回数据
     */
    public ResultVO(ResponseCode code, T data) {
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
