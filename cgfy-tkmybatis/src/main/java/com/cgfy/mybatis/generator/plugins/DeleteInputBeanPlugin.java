package com.cgfy.mybatis.generator.plugins;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.ArrayList;
import java.util.List;

public class DeleteInputBeanPlugin extends AbstractBeanCreatePlugin {
	private static final String BEAN_KEY = "DELETE_INPUT_BEAN";
	private static final String CLASS_NAME = "#TYPE#DeleteInputBean";

	protected void addFieldAnnotation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, List<String> annotationList) {
		annotationList.add("@ApiModelProperty(value = \"" + introspectedColumn.getRemarks() + "\")");
	}

	protected List<IntrospectedColumn> columnList(IntrospectedTable introspectedTable) {
		List<IntrospectedColumn> columnList = new ArrayList();
		for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
			columnList.add(introspectedColumn);
		}
		return columnList;
	}

	protected void addClassAnnotation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty"));
	}

	protected String getClassComment(IntrospectedTable introspectedTable) {
		return introspectedTable.getRemarks() + "删除用Bean";
	}

	protected String createClassName(IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType baseRecordType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

		String basePackage = this.properties.getProperty("basePackage");
		String inputClassName = this.properties.getProperty("fileName");
		if (StringUtils.isEmpty(inputClassName)) {
			inputClassName = "#TYPE#DeleteInputBean";
		}
		String className = inputClassName.replaceAll("#TYPE#", baseRecordType.getShortName());
		return StringUtils.join(new String[] { basePackage, className }, ".");
	}

	protected String getBeanKey() {
		return "DELETE_INPUT_BEAN";
	}
}
