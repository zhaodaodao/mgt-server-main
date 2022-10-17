package com.example.mgtserver.enums;

/**
 * @author Hexrt
 * @description 状态信息
 * @create 2022-03-11 20:28
 */
public enum ResponseCode {
    /**
     * 正常
     */
    OK(200, "success"),
    /**
     * 失败
     */
    FAILED(201, "failed"),
    /**
     * 未定义失败
     */
    UNDEFINED_FAILED(201, "failed"),
    /**
     * 未定义正常
     */
    UNDEFINED_OK(200, "success");
    /**
     * 状态号
     */
    public Integer code;
    /**
     * 状态描述
     */
    public String message;

    /**
     * 返回状态代码
     *
     * @param code    状态号
     * @param message 状态描述
     */
    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 快速自动响应码<br>
     * FAILED:<br>
      *     0,null，异常<br>
     * OK:<br>
     *     其他
     * @param v 数值
     * @param <V> 泛型数值
     * @return 返回码
     */
    public static <V> ResponseCode auto(V v) {
        if (null == v || v instanceof Exception) {
            return ResponseCode.FAILED;
        }
        if (v instanceof Number) {
            if ((v instanceof Integer && v.equals(0))) {
                return ResponseCode.FAILED;
            }
            if((v instanceof Long && v.equals(0L))) {
                return ResponseCode.FAILED;
            }
            if ((v instanceof Short && v.equals(0))) {
                return ResponseCode.FAILED;
            }
            if ((v instanceof Double && ((Double)v).isNaN())) {
                return ResponseCode.FAILED;
            }
        }
        return ResponseCode.OK;
    }

    //todo 以下的自动写法在多线程情况下会出现消息混乱问题
    /**
     * ok返回快速方法
     *
     * @param message 成功描述
     * @return 默认ok返回的responseCode
     */
    public static ResponseCode okOf(String message) {
        ResponseCode code = UNDEFINED_OK;
        code.setMessage(message);
        return code;
    }


    /**
     * 错误返回快速方法
     *
     * @param message 错误描述
     * @return 默认错误返回的responseCode
     */
    public static ResponseCode errorOf(String message) {
        ResponseCode code = UNDEFINED_FAILED;
        code.setMessage(message);
        return code;
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
}
