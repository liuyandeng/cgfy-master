package com.cgfy.user.generator.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.util.JavaBeansUtil;

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
