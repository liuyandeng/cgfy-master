package com.cgfy.user.generator.ext;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;

public class IntrospectedColumnEx extends IntrospectedColumn {
	private boolean manually = false;
	private boolean hidden = false;

	public void setRemarks(String remarks) {
		if (StringUtils.isNotEmpty(remarks)) {
			String[] remarkArr = remarks.split("\\|");
			remarks = remarkArr[0];
			changeRemarks(remarkArr);
			remarks = remarks.replaceAll("\r\n", " ");
		}
		this.remarks = remarks;
	}

	public void setIntrospectedTable(IntrospectedTable introspectedTable) {
		super.setIntrospectedTable(introspectedTable);
		String ignoreColumn = introspectedTable.getContext().getProperties().getProperty("ignoreColumn");
		if (StringUtils.isNotEmpty(ignoreColumn)) {
			String[] ignoreColumns = ignoreColumn.split(",");
			for (String ignoreColumnItem : ignoreColumns) {
				if (StringUtils.equals(ignoreColumnItem, this.javaProperty)) {
					this.hidden = true;
				}
			}
		}
	}

	private void changeRemarks(String[] remarks) {
		for (String remark : remarks) {
			if (StringUtils.equals(remark, "MANUALLY")) {
				this.manually = true;
			}
		}
	}

	public boolean isHidden() {
		return this.hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isManually() {
		return this.manually;
	}

	public void setManually(boolean manually) {
		this.manually = manually;
	}
}