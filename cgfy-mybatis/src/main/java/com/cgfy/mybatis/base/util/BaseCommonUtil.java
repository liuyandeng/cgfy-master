
package com.cgfy.mybatis.base.util;

import com.cgfy.mybatis.base.bean.SelectInputBean;
import com.cgfy.mybatis.base.bean.select.Condition;
import com.cgfy.mybatis.base.bean.select.Direction;
import com.cgfy.mybatis.base.bean.select.Order;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;

public class BaseCommonUtil {

	/***
	 * 获取随机主见ID
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static  boolean isExists(List<Condition> condition, String prop){
		for (Condition con:condition) {
			if(con.getProperty().equals(prop))return true;

		}
		return false;
	}

	public static boolean isExistsClass(List<Condition> condition,String prop,Class classes){
		for (Condition con:condition) {
			if(con.getProperty().equals(prop)){
				if(con.getClass().equals(classes)){
					return true;
				}
			}


		}
		return false;
	}
	/**
	 * 将查询条件转为Example模版
	 * @param input
	 * @param example
	 * @param o
	 */
	public static void inputToExample(SelectInputBean input, Example example, Class o){
		// 检索项目设定
		if (input.getFields() != null && input.getFields().size() != 0) {
			for (String field : input.getFields()) {
				example = example.selectProperties(field);
			}
		}

		// 检索条件变换
		if (input.getCondition() != null && input.getCondition().size() != 0) {
			Example.Criteria criteria = example.createCriteria();
			for (Condition item : input.getCondition()) {
				item.addCondition(example, criteria, o);
			}
		}

		// 排序设定
		if (input.getSort() != null && input.getSort().size() != 0) {
			for (Order order : input.getSort()) {
				if (order.getDirection() == Direction.DESC) {
					example.orderBy(order.getProperty()).desc();
				} else {
					example.orderBy(order.getProperty()).asc();
				}
			}
		}
	}

}
