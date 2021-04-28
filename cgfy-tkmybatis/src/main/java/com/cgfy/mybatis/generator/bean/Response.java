package com.cgfy.mybatis.generator.bean;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 通用Response类
 */
public class Response<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -966198037820471167L;

    /**
     * 是否成功
     */
    @ApiModelProperty(value = "是否成功", required = true)
    private boolean success;
    
    /**
     * 状态代码
     */
    @ApiModelProperty(value = "状态代码", required = true)
    private int statusCode;
    
    /**
     * 返回消息
     */
    @ApiModelProperty(value = "返回消息")
    private String message;
    
    /**
     * 数据
     */
    @ApiModelProperty(value = "数据")
    private T data;
    

    public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

    //构造方法
	public Response() {
        this.success = true;
        this.statusCode = HttpStatus.OK.value();
    }
    
    public Response(boolean success, int statusCode) {
        this.success = success;
        this.statusCode = statusCode;
    }
    
    public Response(boolean success, int statusCode, T data) {
    	this(success, statusCode);
        this.data = data;
    }
    
    public Response(boolean success, int statusCode, String message) {
    	this(success, statusCode);
        this.message = message;
    }
    
    public Response(boolean success, int statusCode, String message, T data) {
        this(success, statusCode, message);
        this.data = data;
    }

    //成功时调用
    public static Response<Object> success() {
        return new Response<Object>();
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>(true, HttpStatus.OK.value(), data);
    }

    public static <T> Response<T> success(T data, String message) {//liuyandeng add
        return new Response<T>(true, HttpStatus.OK.value(),message, data);
    }


    //失败时候调用
    public static Response<Object> fail(int statusCode, String message) {
    	return new Response<Object>(false, statusCode, message);
    }
    
    
    public static <T> Response<T> fail(int statusCode, String message, T data) {
    	return new Response<T>(false, statusCode, message, data);
    }
    
    public static <T> Response<T> fail(String message, T data) {
    	return new Response<T>(false, HttpStatus.BAD_REQUEST.value(), message, data);
    }
    
}