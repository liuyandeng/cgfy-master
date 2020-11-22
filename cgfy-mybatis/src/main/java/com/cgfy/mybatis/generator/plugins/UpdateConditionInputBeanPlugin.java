package com.cgfy.mybatis.generator.plugins;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.ArrayList;
import java.util.List;

public class UpdateConditionInputBeanPlugin extends AbstractBeanCreatePlugin {
	private static final String BEAN_KEY = "UPDATE_CONDITION_INPUT_BEAN";
	private static final String CLASS_NAME = "#TYPE#UpdateConditionInputBean";

	protected void addFieldAnnotation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, List<String> annotationList) {
	}

	protected List<IntrospectedColumn> columnList(IntrospectedTable introspectedTable) {
		List<IntrospectedColumn> columnList = new ArrayList();

		return columnList;
	}

	protected void addValidationNotNull(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, List<String> annotationList) {
	}

	protected void addClassAnnotation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType javaType = new FullyQualifiedJavaType(this.properties.getProperty("updateSuperClass"));
		javaType.addTypeArgument(
				new FullyQualifiedJavaType((String) introspectedTable.getAttribute("UPDATE_INPUT_BEAN")));
		topLevelClass.setSuperClass(javaType);
	}

	protected String getClassComment(IntrospectedTable introspectedTable) {
		return introspectedTable.getRemarks() + "条件用更新用Bean";
	}

	protected String createClassName(IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType baseRecordType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

		String basePackage = this.properties.getProperty("basePackage");
		String inputClassName = this.properties.getProperty("fileName");
		if (StringUtils.isEmpty(inputClassName)) {
			inputClassName = "#TYPE#UpdateConditionInputBean";
		}
		String className = inputClassName.replaceAll("#TYPE#", baseRecordType.getShortName());
		return StringUtils.join(new String[] { basePackage, className }, ".");
	}

	protected String getBeanKey() {
		return "UPDATE_CONDITION_INPUT_BEAN";
	}
}
