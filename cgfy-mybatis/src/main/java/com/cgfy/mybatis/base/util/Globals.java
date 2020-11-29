package com.cgfy.mybatis.base.util;

import javax.servlet.ServletContext;

/**
 *
 * 该类是全局静态变量类
 *
 * @version 2017-10-24
 * @author liuyandeng
 */
public class Globals {


	private static Globals globals = null;

	/**
	 * 全局servlet 该值会在web容器启动时由SpringContextAware初始化
	 */
	public ServletContext WEB_SERVLET_CONTEXT = null;

	/**
	 * 缓存中码表的属性名
	 */
	public static final String CODE_TABLE_ATTR = "_SYSTEM_CODE_TABLE_CONTENT_KEY_";



	public static Globals getInstance() {
		if (globals != null) {
			return globals;
		}
		synchronized (Globals.class) {
			if (globals == null) {
				globals = new Globals();
			}
			return globals;
		}
	}
}
