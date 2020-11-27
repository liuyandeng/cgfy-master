package com.cgfy.user.generator.ext;

import com.cgfy.user.generator.plugins.AbstractBeanCreatePlugin;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.Iterator;
import java.util.List;

/**
 * 插入输入bean和更新输入bean合并成输入bean
 */
public class InputBeanPlugin extends AbstractBeanCreatePlugin {
	private static final String BEAN_KEY = "INPUT_BEAN";
	private static final String CLASS_NAME = "#TYPE#InputBean";

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
		iterator = columnList.iterator();

		String uuidFieldList = introspectedTable.getContext().getProperty("uuidField");
		String[] uuidFields = uuidFieldList.split("\\|");
		while (iterator.hasNext()) {
			IntrospectedColumnEx column = (IntrospectedColumnEx) IntrospectedColumnEx.class.cast(iterator.next());
			for (String uuidField : uuidFields) {
				String[] item = uuidField.split("\\.");
				if ((item.length == 2)
						&& (StringUtils.equalsIgnoreCase(item[0],
								introspectedTable.getFullyQualifiedTableNameAtRuntime()))
						&& (StringUtils.equalsIgnoreCase(item[1], column.getActualColumnName()))) {
					iterator.remove();
					column.setHidden(true);
				}
			}
		}
		
		return columnList;
	}

	protected void addClassAnnotation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty"));
	}

	protected String getClassComment(IntrospectedTable introspectedTable) {
		return introspectedTable.getRemarks() + "新增更新输入用Bean";
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
