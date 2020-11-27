package com.cgfy.user.base.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModelProperty;

/**
 * 通用Response类
 * 
 */
public class AjaxListResponse<T> implements Serializable {

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
    private List<T> data;
    

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

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public AjaxListResponse() {
        this.success = true;
        this.statusCode = HttpStatus.OK.value();
    }
    
    public AjaxListResponse(boolean success, int statusCode) {
        this.success = success;
        this.statusCode = statusCode;
    }
    
    public AjaxListResponse(boolean success, int statusCode, List<T> data) {
    	this(success, statusCode);
        this.data = data;
    }
    
    public AjaxListResponse(boolean success, int statusCode, String message) {
    	this(success, statusCode);
        this.message = message;
    }
    
    public AjaxListResponse(boolean success, int statusCode, String message, List<T> data) {
        this(success, statusCode, message);
        this.data = data;
    }
    
    public static AjaxListResponse<Object> success() {
        return new AjaxListResponse<Object>();
    }
    
    public static <T> AjaxListResponse<T> success(List<T> data) {
        return new AjaxListResponse<T>(true, HttpStatus.OK.value(), data);
    }
    
    public static AjaxListResponse<Object> fail(int statusCode, String message) {
    	return new AjaxListResponse<Object>(false, statusCode, message);
    }
    
    public static <T> AjaxListResponse<T> fail(String message, List<T> data) {
    	return new AjaxListResponse<T>(false, HttpStatus.BAD_REQUEST.value(), message, data);
    }
    
    public static <T> AjaxListResponse<T> fail(int statusCode, String message, List<T> data) {
    	return new AjaxListResponse<T>(false, statusCode, message, data);
    }
    
}