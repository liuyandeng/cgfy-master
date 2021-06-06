package com.cgfy.mybatis.generator.plugins;

import com.cgfy.mybatis.generator.plugins.AbstractBeanCreatePlugin;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.Iterator;
import java.util.List;

public class VoPlugin extends AbstractBeanCreatePlugin {
	private static final String BEAN_KEY = "VOBEAN";
	private static final String CLASS_NAME = "#TYPE#VoBean";

	protected void addFieldAnnotation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, List<String> annotationList) {
		annotationList.add("@ApiModelProperty(value = \"" + introspectedColumn.getRemarks() + "\")");
	}

	protected List<IntrospectedColumn> columnList(IntrospectedTable introspectedTable) {
		List<IntrospectedColumn> columnList = super.columnList(introspectedTable);
		Iterator<IntrospectedColumn> iterator = columnList.iterator();
		while (iterator.hasNext()) {
			IntrospectedColumn column = (IntrospectedColumn) iterator.next();
			if (column.isAutoIncrement()) {
				iterator.remove();
			}
		}
		return columnList;
	}

	protected void addValidationNotNull(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, List<String> annotationList) {
	}

	protected void addClassAnnotation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty"));
	}

	protected String getClassComment(IntrospectedTable introspectedTable) {
		return introspectedTable.getRemarks() + "视图VO";
	}

	protected String createClassName(IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType baseRecordType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

		String basePackage = this.properties.getProperty("basePackage");
		String inputClassName = this.properties.getProperty("fileName");
		if (StringUtils.isEmpty(inputClassName)) {
			inputClassName = CLASS_NAME;
		}
		String className = inputClassName.replaceAll("#TYPE#", baseRecordType.getShortName());
		return StringUtils.join(new String[] { basePackage, className }, ".");
	}

	protected String getBeanKey() {
		return BEAN_KEY;
	}
}
