package com.cgfy.mybatis.generator.util;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import java.util.ArrayList;
import java.util.List;

public class GeneratorUtils {
	public static void addJavaDoc(JavaElement element, String javaDocItem) {
		List<String> javaDocItems = new ArrayList();
		addJavaDoc(element, javaDocItems);
	}

	public static void addJavaDoc(JavaElement element, List<String> javaDocItems) {
		element.addJavaDocLine("/**");
		for (String javaDocItem : javaDocItems) {
			if (StringUtils.isNotEmpty(javaDocItem)) {
				element.addJavaDocLine(" * " + javaDocItem);
			} else {
				element.addJavaDocLine(" *");
			}
		}
		element.addJavaDocLine(" */");
	}

	public static void createField(TopLevelClass topLevelClass, FullyQualifiedJavaType fqjt, String javaProperty,
			String name, List<String> annotationList) {
		topLevelClass.addField(getJavaBeansField(fqjt, javaProperty, name, annotationList));
		topLevelClass.addMethod(getJavaBeansGetter(fqjt, javaProperty, name));
		topLevelClass.addMethod(getJavaBeansSetter(fqjt, javaProperty, name));
	}

	public static Field getJavaBeansField(FullyQualifiedJavaType fqjt, String javaProperty, String name,
			List<String> annotationList) {
		String property = javaProperty;

		Field field = new Field();
		field.setVisibility(JavaVisibility.PRIVATE);
		field.setType(fqjt);
		field.setName(property);

		List<String> javaDocList = new ArrayList();
		javaDocList.add(name);

		addJavaDoc(field, javaDocList);
		if (annotationList != null) {
			for (String annotation : annotationList) {
				field.addAnnotation(annotation);
			}
		}
		return field;
	}

	public static Method getJavaBeansGetter(FullyQualifiedJavaType fqjt, String javaProperty, String name) {
		String property = javaProperty;

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(fqjt);
		method.setName(JavaBeansUtil.getGetterMethodName(property, fqjt));

		StringBuilder sb = new StringBuilder();
		sb.append("return ");
		sb.append(property);
		sb.append(';');
		method.addBodyLine(sb.toString());

		List<String> javaDocList = new ArrayList();
		javaDocList.add("获取" + name);
		javaDocList.add("");
		javaDocList.add(StringUtils.join(new String[] { "@return", name }, " "));

		addJavaDoc(method, javaDocList);

		return method;
	}

	public static Method getJavaBeansSetter(FullyQualifiedJavaType fqjt, String javaProperty, String name) {
		String property = javaProperty;

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName(JavaBeansUtil.getSetterMethodName(property));
		method.addParameter(new Parameter(fqjt, property));

		StringBuilder sb = new StringBuilder();
		sb.append("this.");
		sb.append(property);
		sb.append(" = ");
		sb.append(property);
		sb.append(';');
		method.addBodyLine(sb.toString());

		List<String> javaDocList = new ArrayList();
		javaDocList.add("设置" + name);
		javaDocList.add("");
		javaDocList.add(StringUtils.join(new String[] { "@param", property, name }, " "));

		addJavaDoc(method, javaDocList);

		return method;
	}
}
