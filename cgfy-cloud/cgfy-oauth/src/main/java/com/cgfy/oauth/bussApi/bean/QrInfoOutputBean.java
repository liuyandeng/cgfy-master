package com.cgfy.oauth.bussApi.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value="获取二维码返回信息实体")
public class QrInfoOutputBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "uuid，唯一主键")
	private String uuid;
	
	@ApiModelProperty(value = "二维码登录地址")
	private String login_url;
	
	@ApiModelProperty(value = "二维码文件（base64编码字符串）")
	private String image;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getLogin_url() {
		return login_url;
	}

	public void setLogin_url(String login_url) {
		this.login_url = login_url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
