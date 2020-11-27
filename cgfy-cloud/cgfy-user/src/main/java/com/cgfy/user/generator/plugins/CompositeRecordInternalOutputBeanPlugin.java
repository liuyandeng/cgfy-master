package com.cgfy.user.generator.plugins;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import com.cgfy.user.generator.util.GeneratorUtils;

public class CompositeRecordInternalOutputBeanPlugin extends CompositeModelPlugin {
	private static final String BEAN_KEY = "COMPOSITE_RECORD_INTERNAL_OUTPUT_BEAN";
	private static final String CLASS_NAME = "#TYPE#CompositeInternalOutputBean";

	protected void createBefore(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			List<IntrospectedColumn> columnList) {
		topLevelClass.addMethod(getConstructed(topLevelClass.getType()));

		topLevelClass.addMethod(getConstructed(topLevelClass.getType(),
				new FullyQualifiedJavaType((String) introspectedTable.getAttribute("COMPOSITE_MODEL_BEAN")),
				columnList));
	}

	public Method getConstructed(FullyQualifiedJavaType fqjt) {
		Method method = new Method();
		method.setConstructor(true);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName(fqjt.getShortName());
		method.addBodyLine("");

		List<String> javaDocList = new ArrayList();
		javaDocList.add("默认构造函数");
		GeneratorUtils.addJavaDoc(method, javaDocList);

		return method;
	}

	public Method getConstructed(FullyQualifiedJavaType fqjt, FullyQualifiedJavaType param,
			List<IntrospectedColumn> columnList) {
		Method method = new Method();
		method.setConstructor(true);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName(fqjt.getShortName());
		method.addParameter(new Parameter(param, "input"));
		method.addBodyLine("");
		for (IntrospectedColumn introspectedColumn : columnList) {
			method.addBodyLine("// " + introspectedColumn.getRemarks());
			method.addBodyLine("this." + introspectedColumn.getJavaProperty() + " = input."
					+ JavaBeansUtil.getGetterMethodName(introspectedColumn.getJavaProperty(),
							introspectedColumn.getFullyQualifiedJavaType())
					+ "();");
		}
		Object javaDocList = new ArrayList();
		((List) javaDocList).add("默认构造函数");
		GeneratorUtils.addJavaDoc(method, (List) javaDocList);

		return method;
	}

	protected void addFieldAnnotation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, List<String> annotationList) {
		annotationList.add("@ApiModelProperty(value = \"" + introspectedColumn.getRemarks() + "\")");
	}

	protected void addClassAnnotation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty"));
	}

	protected String getClassComment(IntrospectedTable introspectedTable) {
		return introspectedTable.getRemarks() + "输出用Bean";
	}

	protected String createClassName(IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType baseRecordType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

		String basePackage = this.properties.getProperty("basePackage");
		String inputClassName = this.properties.getProperty("fileName");
		if (StringUtils.isEmpty(inputClassName)) {
			inputClassName = "#TYPE#CompositeInternalOutputBean";
		}
		String className = inputClassName.replaceAll("#TYPE#", baseRecordType.getShortName());
		return StringUtils.join(new String[] { basePackage, className }, ".");
	}

	protected String getBeanKey() {
		return "COMPOSITE_RECORD_INTERNAL_OUTPUT_BEAN";
	}
}
