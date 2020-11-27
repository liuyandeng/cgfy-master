package com.cgfy.user.generator.plugins;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class ModelPlugin extends PluginAdapter {
	private List<String> superInterface = new ArrayList();
	private String superClass;

	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		for (String superInterfaceItem : this.superInterface) {
			topLevelClass.addImportedType(new FullyQualifiedJavaType(superInterfaceItem));
			topLevelClass.addSuperInterface(new FullyQualifiedJavaType(superInterfaceItem));
		}
		if (StringUtils.isNotEmpty(this.superClass)) {
			topLevelClass.addImportedType(new FullyQualifiedJavaType(this.superClass));
			topLevelClass.setSuperClass(new FullyQualifiedJavaType(this.superClass));
		}
		return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
	}

	public void setProperties(Properties properties) {
		super.setProperties(properties);
		String supertInterface = this.properties.getProperty("supertInterface");
		String superClass = this.properties.getProperty("superClass");
		if (StringUtils.isNotEmpty(supertInterface)) {
			for (String supertInterfaceItem : supertInterface.split(",")) {
				this.superInterface.add(supertInterfaceItem);
			}
		}
		if (StringUtils.isNotEmpty(superClass)) {
			this.superClass = superClass;
		}
	}

	public boolean validate(List<String> warnings) {
		return true;
	}
}
