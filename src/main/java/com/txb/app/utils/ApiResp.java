package com.txb.app.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shiva   2021/9/1 13:42
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResp<T> implements Serializable {

    @ApiModelProperty(value = "状态，0-成功，1-失败")
    private int code;

    @ApiModelProperty(value = "信息")
    private String msg;

    @ApiModelProperty(value = "数据体")
    private T data;

    /**
     * 失败
     */
    public static<T> ApiResp<T> error(int code,String msg,T data) {
        return new ApiResp<>(500, msg, null);
    }
    /**
     * 失败，不带错误信息
     */
    public static<T> ApiResp<T> error() {
        return new ApiResp<>(500, null, null);
    }

    /**
     * 成功，不带返回数据
     */
    public static<T> ApiResp<T> success(int code,String msg,T data) {
        return new ApiResp<>(200, msg, null);
    }

    /**
     * 成功，不带返回数据
     */
    public static<T> ApiResp<T> success() {
        return new ApiResp<>(200, "请求成功", null);
    }
}
