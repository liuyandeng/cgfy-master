package com.cgfy.mybatis.generator.plugins;

import com.cgfy.mybatis.generator.util.GeneratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.*;

public class CompositeMapperPlugin extends PluginAdapter {
	private static final String BEAN_KEY = "COMPOSITE_MAPPER_BEAN";
	private static final String CLASS_NAME = "#TYPE#CompositeMapper";
	private Set<String> mappers = new HashSet();

	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		List<GeneratedJavaFile> answer = new ArrayList();

		String classFullName = createClassName(introspectedTable);

		Interface topLevelClass = createTopLevelClass(classFullName, introspectedTable);

		introspectedTable.setAttribute(getBeanKey(), classFullName);
		introspectedTable.setAttribute(getBeanKey() + "_SHORT", topLevelClass.getType().getShortName());
		addClassAnnotation(topLevelClass, introspectedTable);

		addClassComment(topLevelClass, introspectedTable);

		answer.add(createGeneratedJavaFile(topLevelClass));

		return answer;
	}

	protected GeneratedJavaFile createGeneratedJavaFile(CompilationUnit topLevelClass) {
		GeneratedJavaFile gjf = new GeneratedJavaFile(topLevelClass,
				this.context.getJavaModelGeneratorConfiguration().getTargetProject(),
				this.context.getProperty("javaFileEncoding"), this.context.getJavaFormatter());
		return gjf;
	}

	private void addClassComment(JavaElement innerClass, IntrospectedTable introspectedTable) {
		String commentClassAuthor = getContext().getProperties().getProperty("commentClassAuthor");
		List<String> javaDocItems = new ArrayList();
		javaDocItems.add(getClassComment(introspectedTable));
		javaDocItems.add(null);
		javaDocItems.add("@author " + commentClassAuthor);
		GeneratorUtils.addJavaDoc(innerClass, javaDocItems);
	}

	private Interface createTopLevelClass(String className, IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(className);
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		for (String mapper : this.mappers) {
			interfaze.addImportedType(new FullyQualifiedJavaType(mapper));
			interfaze.addSuperInterface(new FullyQualifiedJavaType(
					mapper + "<" + introspectedTable.getAttribute("COMPOSITE_MODEL_BEAN_SHORT") + ">"));
		}
		return interfaze;
	}

	protected List<IntrospectedColumn> columnList(IntrospectedTable introspectedTable) {
		List<IntrospectedColumn> columnList = new ArrayList();
		String contextIgnoreColumn = getContext().getProperties().getProperty("ignoreColumn");
		String localContextIgnoreColumn = this.properties.getProperty("ignoreColumn");

		String[] ignoreColumns = new String[0];
		if (StringUtils.isNotEmpty(contextIgnoreColumn)) {
			ignoreColumns = contextIgnoreColumn.split(",");
		}
		if (StringUtils.isNotEmpty(localContextIgnoreColumn)) {
			ignoreColumns = localContextIgnoreColumn.split(",");
		}
		for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
			boolean search = false;
			for (String ignoreColumn : ignoreColumns) {
				if (StringUtils.equals(ignoreColumn, introspectedColumn.getJavaProperty())) {
					search = true;
					break;
				}
			}
			if (!search) {
				columnList.add(introspectedColumn);
			}
		}
		return columnList;
	}

	protected void addValidation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, List<String> annotationList) {
		addValidationOther(topLevelClass, introspectedTable, introspectedColumn, annotationList);
		addValidationNotNull(topLevelClass, introspectedTable, introspectedColumn, annotationList);
	}

	protected void addValidationNotNull(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, List<String> annotationList) {
		if (!introspectedColumn.isNullable()) {
			annotationList.add("@NotNull");
			topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.validation.constraints.NotNull"));
		}
	}

	protected void addValidationOther(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, List<String> annotationList) {
		if (introspectedColumn.getJdbcType() == JdbcType.VARCHAR.ordinal()) {
			annotationList.add("@Size(max = " + introspectedColumn.getLength() + ")");
			topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.validation.constraints.Size"));
		}
	}

	protected void addClassAnnotation(Interface topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper"));
		topLevelClass.addImportedType(
				new FullyQualifiedJavaType((String) introspectedTable.getAttribute("COMPOSITE_MODEL_BEAN")));
		topLevelClass.addAnnotation("@Mapper");
	}

	protected String getClassComment(IntrospectedTable introspectedTable) {
		return introspectedTable.getRemarks() + "复合Mapper";
	}

	protected String createClassName(IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType baseRecordType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

		String basePackage = this.properties.getProperty("basePackage");
		String inputClassName = this.properties.getProperty("fileName");
		if (StringUtils.isEmpty(inputClassName)) {
			inputClassName = "#TYPE#CompositeMapper";
		}
		String className = inputClassName.replaceAll("#TYPE#", baseRecordType.getShortName());
		return StringUtils.join(new String[] { basePackage, className }, ".");
	}

	protected String getBeanKey() {
		return "COMPOSITE_MAPPER_BEAN";
	}

	public boolean validate(List<String> warnings) {
		return true;
	}

	public void setProperties(Properties properties) {
		super.setProperties(properties);
		String mappers = this.properties.getProperty("mappers");
		if (StringUtility.stringHasValue(mappers)) {
			for (String mapper : mappers.split(",")) {
				this.mappers.add(mapper);
			}
		} else {
			throw new RuntimeException("Mapper插件缺少必要的mappers属性!");
		}
	}
}
