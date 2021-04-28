package ${package};

import ${props.fwPackage}.base.service.BaseService;
import ${props.fwPackage}.base.bean.CgfyListResponse;
import ${props.fwPackage}.base.bean.CgfySelectInputBean;
import ${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN")};
import ${tableClass.getIntrospectedTable().getAttribute("INPUT_BEAN")};

/**
 * 「${tableClass.introspectedTable.remarks}」基础Service
 *
 * @author ${props.author}
 */
public interface ${tableClass.shortClassName}Service extends BaseService{


	/**
	* 检索
	* @param cgfyInput 输入参数
	* @return 输出对象
	*/
	public CgfyListResponse<${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")}> select(CgfySelectInputBean CgfyInput);

	/**
	* 保存
	* @param input 输入参数
	* @param <#list tableClass.pkFields as field>${field.fieldName}</#list> 主键id
	* @return 输出对象
	*/
	public ${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")} save(${tableClass.getIntrospectedTable().getAttribute("INPUT_BEAN_SHORT")} input<#list tableClass.pkFields as field>,${field.shortTypeName} ${field.fieldName}</#list>);

	/**
	* 获取详情
	* @param <#list tableClass.pkFields as field>${field.fieldName}</#list> 主键id
	* @return 输出对象
	*/
	public ${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")} getDetail(<#list tableClass.pkFields as field>${field.shortTypeName} ${field.fieldName}</#list>);

	/**
	* 物理删除
	* @param <#list tableClass.pkFields as field>${field.fieldName}</#list> 主键id
	* @return 输出对象
	*/
	public void deleteForce(<#list tableClass.pkFields as field>${field.shortTypeName} ${field.fieldName}</#list>);



}

