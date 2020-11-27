package com.cgfy.user.base.util;

public class BaseConstant {
	/** 默认树形结构跟节点ID **/
	public static final String DEFAULT_TREE_STRUCTURE_ROOT_ID = "****";

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

	// 审核记录默认暂存状态
	private static final int AUDIT_RECORD_DEFAULT_SAVE_STATUS = 0;

	// 审核记录默认审核中状态
	private static final int AUDIT_RECORD_DEFAULT_AUDITING_STATUS = 1;

	// 审核记录默认审核通过状态
	private static final int AUDIT_RECORD_DEFAULT_AUDIT_PASS_STATUS = 2;

	// 审核记录默认审核驳回状态
	private static final int AUDIT_RECORD_DEFAULT_AUDIT_REJECT_STATUS = 3;

	// 审核记录默认审核驳回状态
	private static final int AUDIT_RECORD_DEFAULT_AUDIT_END_STATUS = 4;



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

	public static int getAuditRecordDefaultSaveStatus() {
		return AUDIT_RECORD_DEFAULT_SAVE_STATUS;
	}

	public static int getAuditRecordDefaultAuditingStatus() {
		return AUDIT_RECORD_DEFAULT_AUDITING_STATUS;
	}

	public static int getAuditRecordDefaultAuditPassStatus() {
		return AUDIT_RECORD_DEFAULT_AUDIT_PASS_STATUS;
	}

	public static int getAuditRecordDefaultAuditRejectStatus() {
		return AUDIT_RECORD_DEFAULT_AUDIT_REJECT_STATUS;
	}

	public static int getAuditRecordDefaultAuditEndStatus() {
		return AUDIT_RECORD_DEFAULT_AUDIT_END_STATUS;
	}

}
