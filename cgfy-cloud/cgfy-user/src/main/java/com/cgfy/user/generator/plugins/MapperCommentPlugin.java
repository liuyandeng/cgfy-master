package com.cgfy.user.generator.plugins;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import com.cgfy.user.generator.util.GeneratorUtils;

public class MapperCommentPlugin extends PluginAdapter {
	
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		GeneratorUtils.addJavaDoc(interfaze, getClassDoc(introspectedTable, " Mapper"));
		return true;
	}

	private List<String> getClassDoc(IntrospectedTable introspectedTable, String suffix) {
		String commentClassAuthor = introspectedTable.getContext().getProperty("commentClassAuthor");
		List<String> javaDocItems = new ArrayList();
		if (StringUtils.isNotEmpty(introspectedTable.getRemarks())) {
			javaDocItems.add(introspectedTable.getRemarks() + suffix);
			javaDocItems.add(null);
		}
		if (StringUtils.isNotEmpty(commentClassAuthor)) {
			javaDocItems.add("@author " + commentClassAuthor);
		}
		return javaDocItems;
	}

	public boolean validate(List<String> warnings) {
		return true;
	}
}
