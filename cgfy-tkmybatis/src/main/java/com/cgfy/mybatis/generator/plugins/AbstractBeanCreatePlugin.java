package com.cgfy.mybatis.generator.plugins;

import com.cgfy.mybatis.generator.util.GeneratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SuppressWarnings({"rawtypes","unchecked"})
public abstract class AbstractBeanCreatePlugin extends PluginAdapter {
	
	private List<String> superInterface = new ArrayList();
	private String superClass;

	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		List<GeneratedJavaFile> answer = new ArrayList();

		String classFullName = createClassName(introspectedTable);

		TopLevelClass topLevelClass = createTopLevelClass(classFullName);
		for (String superInterfaceItem : this.superInterface) {
			topLevelClass.addImportedType(new FullyQualifiedJavaType(superInterfaceItem));
			topLevelClass.addSuperInterface(new FullyQualifiedJavaType(superInterfaceItem));
		}
		if (StringUtils.isNotEmpty(this.superClass)) {
			topLevelClass.addImportedType(new FullyQualifiedJavaType(this.superClass));
			topLevelClass.setSuperClass(new FullyQualifiedJavaType(this.superClass));
		}
		introspectedTable.setAttribute(getBeanKey(), classFullName);
		introspectedTable.setAttribute(getBeanKey() + "_SHORT", topLevelClass.getType().getShortName());
		addClassAnnotation(topLevelClass, introspectedTable);

		addClassComment(topLevelClass, introspectedTable);

		Object columnList = columnList(introspectedTable);

		createBefore(topLevelClass, introspectedTable, (List) columnList);
		createField(topLevelClass, introspectedTable, (List) columnList);
		createAfter(topLevelClass, introspectedTable, (List) columnList);
		answer.add(createGeneratedJavaFile(topLevelClass));

		return answer;
	}

	protected void createBefore(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			List<IntrospectedColumn> columnList) {
	}

	protected void createAfter(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			List<IntrospectedColumn> columnList) {
	}

	private void createField(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			List<IntrospectedColumn> columnList) {
		for (IntrospectedColumn introspectedColumn : columnList) {
			List<String> annotationList = new ArrayList();
			addFieldAnnotation(topLevelClass, introspectedTable, introspectedColumn, annotationList);
			addValidation(topLevelClass, introspectedTable, introspectedColumn, annotationList);
			topLevelClass.addField(GeneratorUtils.getJavaBeansField(introspectedColumn.getFullyQualifiedJavaType(),
					introspectedColumn.getJavaProperty(), introspectedColumn.getRemarks(), annotationList));
		}
		for (IntrospectedColumn introspectedColumn : columnList) {
			topLevelClass.addMethod(GeneratorUtils.getJavaBeansGetter(introspectedColumn.getFullyQualifiedJavaType(),
					introspectedColumn.getJavaProperty(), introspectedColumn.getRemarks()));
			topLevelClass.addMethod(GeneratorUtils.getJavaBeansSetter(introspectedColumn.getFullyQualifiedJavaType(),
					introspectedColumn.getJavaProperty(), introspectedColumn.getRemarks()));
		}
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

	private TopLevelClass createTopLevelClass(String className) {
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(className);
		TopLevelClass topLevelClass = new TopLevelClass(type);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		return topLevelClass;
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

	protected abstract void addFieldAnnotation(TopLevelClass paramTopLevelClass,
			IntrospectedTable paramIntrospectedTable, IntrospectedColumn paramIntrospectedColumn,
			List<String> paramList);

	protected abstract void addClassAnnotation(TopLevelClass paramTopLevelClass,
			IntrospectedTable paramIntrospectedTable);

	protected abstract String getClassComment(IntrospectedTable paramIntrospectedTable);

	protected abstract String createClassName(IntrospectedTable paramIntrospectedTable);

	protected abstract String getBeanKey();

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
