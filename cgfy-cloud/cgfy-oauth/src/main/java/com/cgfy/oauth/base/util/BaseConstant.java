package com.cgfy.oauth.base.util;

public class BaseConstant {
	/** 默认树形结构跟节点ID **/
	private static final String DEFAULT_TREE_STRUCTURE_ROOT_ID = "****";
	
	/** 默认树形结构跟节点ID **/
	private static final int DEFAULT_TREE_STRUCTURE_START_LEVEL = 0;
	
	// 记录默认删除状态
	private static final String RECORD_DEFAULT_DELETE_STATUS = "2";
	
	// 记录默认有效状态
	private static final String RECORD_DEFAULT_ACTIVE_STATUS = "0";
	
	// 真假中：真的默认值
	private static final String BOOLEAN_TRUE_DEFAULT = "1";
	
	// 真假中：假的默认值
	private static final String BOOLEAN_FALSE_DEFAULT = "0";
	

	public static String getDefaultTreeStructureRootId() {
		return DEFAULT_TREE_STRUCTURE_ROOT_ID;
	}

	public static int getDefaultTreeStructureStartLevel() {
		return DEFAULT_TREE_STRUCTURE_START_LEVEL;
	}

	public static String getRecordDefaultDeleteStatus() {
		return RECORD_DEFAULT_DELETE_STATUS;
	}

	public static String getRecordDefaultActiveStatus() {
		return RECORD_DEFAULT_ACTIVE_STATUS;
	}

	public static String getBooleanTrueDefault() {
		return BOOLEAN_TRUE_DEFAULT;
	}

	public static String getBooleanFalseDefault() {
		return BOOLEAN_FALSE_DEFAULT;
	}
	
}
