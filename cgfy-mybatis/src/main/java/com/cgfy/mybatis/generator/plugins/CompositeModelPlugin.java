package com.cgfy.mybatis.generator.plugins;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Properties;

public class CompositeModelPlugin extends AbstractBeanCreatePlugin {
	private static final String BEAN_KEY = "COMPOSITE_MODEL_BEAN";
	private static final String CLASS_NAME = "#TYPE#Composite";

	protected List<IntrospectedColumn> columnList(IntrospectedTable introspectedTable) {
		List<IntrospectedColumn> columnList = introspectedTable.getAllColumns();
		for (IntrospectedColumn column : columnList) {
			column.setTableAlias(column.getIntrospectedTable().getFullyQualifiedTable().getIntrospectedTableName());
		}
		return columnList;
	}

	protected void addFieldAnnotation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, List<String> annotationList) {
		for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
			if (StringUtils.equals(introspectedColumn.getJavaProperty(), column.getJavaProperty())) {
				annotationList.add("@Id");
				break;
			}
		}
		annotationList.add("@Column(name = \"" + introspectedColumn.getTableAlias() + "."
				+ introspectedColumn.getActualColumnName() + "\")");
	}

	protected void addClassAnnotation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.persistence.*"));
		try {
			String tableName = introspectedTable.getFullyQualifiedTable().toString() + " AS "
					+ introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()+ " ";
			
			topLevelClass.addAnnotation("@Table(name = \""+tableName+"\")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getClassComment(IntrospectedTable introspectedTable) {
		return introspectedTable.getRemarks() + "复合Model";
	}

	protected String createClassName(IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType baseRecordType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

		String basePackage = this.properties.getProperty("basePackage");
		String inputClassName = this.properties.getProperty("fileName");
		if (StringUtils.isEmpty(inputClassName)) {
			inputClassName = "#TYPE#Composite";
		}
		String className = inputClassName.replaceAll("#TYPE#", baseRecordType.getShortName());
		return StringUtils.join(new String[] { basePackage, className }, ".");
	}

	protected void addValidation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, List<String> annotationList) {
	}

	protected String getBeanKey() {
		return "COMPOSITE_MODEL_BEAN";
	}

	public void setProperties(Properties properties) {
		super.setProperties(properties);
	}
}
