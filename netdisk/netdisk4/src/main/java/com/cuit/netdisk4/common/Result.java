package com.cuit.netdisk4.common;

import java.io.Serializable;

/**
 * 通用返回结果类
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message;
    private Object data;

    public Result() {}

    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("操作成功");
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    public static Result error(String message) {
        Result result = new Result();
        result.setCode(500);
        result.setMessage(message);
        return result;
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

    public void setData(Object data) {
        this.data = data;
    }
}