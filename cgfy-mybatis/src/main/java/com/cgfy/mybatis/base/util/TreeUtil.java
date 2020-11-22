package com.cgfy.mybatis.base.util;


import com.cgfy.mybatis.base.bean.TreeNodeBean;
import com.cgfy.mybatis.base.bean.TreeOutputBean;

import java.util.*;

public class TreeUtil {

	/**
	 * 根据上级ID获取树形结构
	 * 
	 * @param treeNodeList
	 *            属性节点列表
	 * @param parentId
	 *            上级ID
	 * @return
	 */
	public static List<TreeOutputBean> getTreeByParentId(List<TreeNodeBean> treeNodeList, String parentId) {
		List<TreeOutputBean> treeOutputs = new ArrayList<TreeOutputBean>();
		Map<String, List<TreeNodeBean>> treeMap = new HashMap<String, List<TreeNodeBean>>();
		/** 构造树形map **/
		if (treeNodeList != null && !treeNodeList.isEmpty()) {
			for (TreeNodeBean treeNode : treeNodeList) {
				String pId = Objects.toString(treeNode.getParent_id());
				List<TreeNodeBean> tempList = treeMap.get(pId);
				if(tempList==null || tempList.isEmpty()) {
					tempList = new ArrayList<TreeNodeBean>();
				}
				tempList.add(treeNode);
				treeMap.put(pId, tempList);
			}
		}
		
		/** 递归获取权限树形菜单 **/
		if (treeMap != null && !treeMap.isEmpty()) {
			List<TreeNodeBean> tempList = treeMap.get(parentId);
			treeOutputs = getTreeRecord(tempList, treeMap, BaseConstant.getDefaultTreeStructureStartLevel());
		}
		
		return treeOutputs;
	}
	
	/**
	 * 根据ID获取树形结构
	 * 
	 * @param treeNodeList
	 *            属性节点列表
	 * @param id
	 *            ID
	 * @return
	 */
	public static List<TreeOutputBean> getTreeById(List<TreeNodeBean> treeNodeList, String id) {
		id = Objects.toString(id,"");
		List<TreeOutputBean> treeOutputs = new ArrayList<TreeOutputBean>();
		Map<String, List<TreeNodeBean>> treeMap = new HashMap<String, List<TreeNodeBean>>();
		/** 构造树形map **/
		if (treeNodeList != null && !treeNodeList.isEmpty()) {
			//传入主键ID的记录信息
			TreeOutputBean treeRoot = null;
			
			for (TreeNodeBean treeNode : treeNodeList) {
				
				/** 查找节点ID是传入ID的属性节点  **/
				if(id.equals(treeNode.getId())) {
					treeRoot = new TreeOutputBean(treeNode);
				}
				
				String pId = Objects.toString(treeNode.getParent_id());
				List<TreeNodeBean> tempList = treeMap.get(pId);
				if(tempList==null || tempList.isEmpty()) {
					tempList = new ArrayList<TreeNodeBean>();
				}
				tempList.add(treeNode);
				treeMap.put(pId, tempList);
			}
			
			if(treeRoot!=null) {
				List<TreeOutputBean> childTreeOutputs = new ArrayList<TreeOutputBean>();
				/** 递归获取权限树形菜单 **/
				if (treeMap != null && !treeMap.isEmpty()) {
					List<TreeNodeBean> tempList = treeMap.get(id);
					childTreeOutputs = getTreeRecord(tempList, treeMap, BaseConstant.getDefaultTreeStructureStartLevel() + 1);
				}
				treeRoot.setChildren(childTreeOutputs);
				treeRoot.setLevel(BaseConstant.getDefaultTreeStructureStartLevel());
				
				treeOutputs.add(treeRoot);
			}
		}
		
		return treeOutputs;
	}
	
	
	/**
	 * 递归获取树形结构
	 * @param treeNodeList
	 * @param treeMap
	 * @return
	 */
	private static List<TreeOutputBean> getTreeRecord(List<TreeNodeBean> treeNodeList, Map<String, List<TreeNodeBean>> treeMap, int level){
		List<TreeOutputBean> treeOutputs = new ArrayList<TreeOutputBean>();
		
		if(treeNodeList!=null && !treeNodeList.isEmpty()) {
			for(TreeNodeBean treeNode : treeNodeList) {
				
				/** 查询子节点 **/
				List<TreeOutputBean> treeOutputChild = new ArrayList<TreeOutputBean>();
				
				List<TreeNodeBean> treeNodeChildList = treeMap.get(treeNode.getId());
				if(treeNode.getId()!=null && !treeNode.getId().equals(treeNode.getParent_id())) {
					treeOutputChild = getTreeRecord(treeNodeChildList, treeMap, level+1);
				}
				
				/** 构造属性节点 **/
				TreeOutputBean tempRecord = new TreeOutputBean(treeNode);
				tempRecord.setLevel(level);
				tempRecord.setChildren(treeOutputChild);
				
				treeOutputs.add(tempRecord);
			}
		}
		
		return treeOutputs;
	}
	
}
