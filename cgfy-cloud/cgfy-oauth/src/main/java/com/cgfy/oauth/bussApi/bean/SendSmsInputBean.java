package com.cgfy.oauth.bussApi.bean;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 短信发送输入用Bean
 */
public class SendSmsInputBean {
	
    /**
     * 电话号码列表
     */
    @ApiModelProperty(value = "电话号码列表")
    @NotNull
    private List<String> phoneNumbers;

    /**
     * 短信内容
     */
    @ApiModelProperty(value = "短信内容")
    @Size(max = 400)
    @NotNull
    private String smsText;
    

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getSmsText() {
		return smsText;
	}

	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}
	
}