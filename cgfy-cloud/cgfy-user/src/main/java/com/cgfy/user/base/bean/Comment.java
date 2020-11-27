package com.cgfy.user.base.bean;

import lombok.Data;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@ToString
public class Comment {
	private String id;
	private String content;
	private String noBtnOpinion;
	private String action;
	private Integer actionId;
	private String creatorId;
	private String creatorName;
	private String organId;
	private String organName;
	private String created;
	private String node;
	private String formId;

	private String uid;
	private String uname;
	private String oname;
    private String keyId;
	private String agentUid;
	private String agentName;
	private String time;

	public Comment() {
		if (this.created == null){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.created = simpleDateFormat.format(new Date());
		}

	}
	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		if (this.created == null){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.created = simpleDateFormat.format(new Date());
		}else{
			this.created = created;
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
