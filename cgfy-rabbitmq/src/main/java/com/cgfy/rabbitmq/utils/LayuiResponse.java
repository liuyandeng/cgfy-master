package com.cgfy.rabbitmq.utils;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 通用Response类
 */
public class LayuiResponse<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -966198037820471167L;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 状态代码
     */
    private int code;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;
    /**
     * 条数
     */
    private int count;
    /******************************************************构造方法************************************/
	public LayuiResponse() {
        this.success = true;
        this.code = 0;
    }

    public LayuiResponse(boolean success, int code) {
        this.success = success;
        this.code = code;
    }

    public LayuiResponse(boolean success, int code, T data) {
    	this(success, code);
        this.data = data;
    }

    public LayuiResponse(boolean success, int code, String msg) {
    	this(success, code);
        this.msg = msg;
    }

    public LayuiResponse(boolean success, int code, String msg, T data) {//liuyandeng add
        this(success, code, msg);
        this.data = data;
    }
    public LayuiResponse( T data,Integer count) {//liuyandeng add
        this.code = 0;
        this.data = data;
        this.count = count;
    }
    /******************************************************成功时调用******************************************/
    public static LayuiResponse<Object> success() {
        return new LayuiResponse<Object>();
    }
    
    public static <T> LayuiResponse<T> success(T data) {
        return new LayuiResponse<T>(true, 0, data);
    }

    public static <T> LayuiResponse<T> success(T data, String msg) {//liuyandeng add
        return new LayuiResponse<T>(true, 0,msg, data);
    }
    public static <T> LayuiResponse<T> success(T data,int count) {//liuyandeng add
        return new LayuiResponse<T>(data,count);
    }

    /******************************************************失败时候调用*******************************/
    public static LayuiResponse<Object> fail(int code, String msg) {
    	return new LayuiResponse<Object>(false, code, msg);
    }
    public static <T> LayuiResponse<T> fail(int code, String msg, T data) {
    	return new LayuiResponse<T>(false, code, msg, data);
    }
    
    public static <T> LayuiResponse<T> fail(String msg, T data) {
    	return new LayuiResponse<T>(false, HttpStatus.BAD_REQUEST.value(), msg, data);
    }

/******************************************************getset**************************************************/

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}