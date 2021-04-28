package com.cgfy.mybatis.generator.ext;

import com.cgfy.mybatis.generator.util.GeneratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.logging.Log;
import org.mybatis.generator.logging.LogFactory;
import tk.mybatis.mapper.generator.MapperCommentGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapperCommentGeneratorEx extends MapperCommentGenerator {
	private Log logger = LogFactory.getLog(MapperCommentGeneratorEx.class);

	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		super.addFieldComment(field, introspectedTable, introspectedColumn);
		if (introspectedColumn.isAutoIncrement()) {
			field.addAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY)");
		}
		String uuidFieldList = introspectedTable.getContext().getProperty("uuidField");
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("uuidFieldList :" + uuidFieldList);
		}
		String[] uuidFields = uuidFieldList.split("\\|");
		for (String uuidField : uuidFields) {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("  uuidField :" + uuidField);
			}
			String[] item = uuidField.split("\\.");
			if ((item.length == 2)
					&& (StringUtils.equalsIgnoreCase(item[0], introspectedTable.getFullyQualifiedTableNameAtRuntime()))
					&& (StringUtils.equalsIgnoreCase(item[1], introspectedColumn.getActualColumnName()))) {
				List<String> annotations = field.getAnnotations();
				Iterator<String> annotationIterator = annotations.iterator();
				while (annotationIterator.hasNext()) {
					String annotation = (String) annotationIterator.next();
					if (StringUtils.startsWith(annotation, "@GeneratedValue")) {
						annotationIterator.remove();
					}
				}
			}
		}
		if (StringUtils.equals(field.getName(), "serialVersionUID")) {
			GeneratorUtils.addJavaDoc(field, "serialVersionUID");
		}
	}

	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		GeneratorUtils.addJavaDoc(innerClass, getClassDoc(introspectedTable, " Mapper"));
	}

	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		GeneratorUtils.addJavaDoc(topLevelClass, getClassDoc(introspectedTable, ""));
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
}
