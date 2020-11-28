package com.cgfy.oauth.bussApi.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.io.Serializable;

@ApiModel(value="获取二维码登录返回信息实体")
public class QrLoginInfoOutputBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "uuid，唯一主键")
	private String uuid;
	
	@ApiModelProperty(value = "状态：0初始化二维码完成，1已扫描登录，但凭据无效，2扫描登录成功")
	private String status;
	
	@ApiModelProperty(value = "登录消息")
	private String msg;
	
	@ApiModelProperty(value = "token信息")
	private OAuth2AccessToken token_info;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public OAuth2AccessToken getToken_info() {
		return token_info;
	}

	public void setToken_info(OAuth2AccessToken token_info) {
		this.token_info = token_info;
	}
	
}
