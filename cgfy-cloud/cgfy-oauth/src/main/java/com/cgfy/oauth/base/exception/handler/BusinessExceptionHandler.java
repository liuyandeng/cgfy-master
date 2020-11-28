package com.cgfy.oauth.base.exception.handler;

import com.cgfy.oauth.base.bean.AjaxResponse;
import com.cgfy.oauth.base.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK) 
    @ResponseBody
    public AjaxResponse<BusinessException> controllerExceptionHandler(HttpServletRequest req, BusinessException e) {
        return AjaxResponse.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e);
    }
}
