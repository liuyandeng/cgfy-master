package com.cgfy.oauth.bussApi.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 短信发送输出用Bean
 */
public class SendSmsOutputBean {
	
    /**
     * 电话号码
     */
    @ApiModelProperty(value = "电话号码列表")
    private String phoneNumber;

    /**
     * 发送状态码
     */
    @ApiModelProperty(value = "发送状态码")
    private String sendStatusCode;

    /**
     * 发送状态信息
     */
    @ApiModelProperty(value = "发送状态信息")
    private String sendStatusMsg;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSendStatusCode() {
		return sendStatusCode;
	}

	public void setSendStatusCode(String sendStatusCode) {
		this.sendStatusCode = sendStatusCode;
	}

	public String getSendStatusMsg() {
		return sendStatusMsg;
	}

	public void setSendStatusMsg(String sendStatusMsg) {
		this.sendStatusMsg = sendStatusMsg;
	}
	
}