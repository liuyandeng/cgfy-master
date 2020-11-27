package com.cgfy.user.base.exception;

/**
 * 业务异常类
 */
public class BusinessException extends RuntimeException {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8164041950695047364L;
    

    public BusinessException(Throwable cause) {
    	super(cause);
    }
    
    public BusinessException(String message) {
    	super(message);
    }
    
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    
}