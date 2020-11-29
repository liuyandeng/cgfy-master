package ${package};

import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ${props.fwPackage}.base.controller.BaseController;
import ${props.fwPackage}.base.bean.AjaxResponse;
import ${props.fwPackage}.base.bean.CgfyListResponse;
import ${props.fwPackage}.base.bean.CgfySelectInputBean;
import ${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN")};
import ${tableClass.getIntrospectedTable().getAttribute("SERVICE_INTERFACE")};
import ${tableClass.getIntrospectedTable().getAttribute("INPUT_BEAN")};
/**
 * 「${tableClass.introspectedTable.remarks}」基础Controller
 *
 * @author ${props.author}
 */
@Api(tags ="${tableClass.introspectedTable.remarks}", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("${tableClass.getIntrospectedTable().getAttribute("CONTROLLEREX_INTERFACE_SHORT")}")
@RequestMapping("/${tableClass.shortClassName}")
public class ${tableClass.getIntrospectedTable().getAttribute("CONTROLLEREX_INTERFACE_SHORT")} extends  BaseController {

	/**
	* 「${tableClass.introspectedTable.remarks}」Service
	*/
    @Resource
	private ${tableClass.getIntrospectedTable().getAttribute("SERVICE_INTERFACE_SHORT")} service;




    /**
    * 检索
    * @param cgfyInput 查询参数
    * @return 输入对象
    */
    @ApiOperation(value = "检索", hidden=true)
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public AjaxResponse<CgfyListResponse<${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")}>> select(@RequestBody  @ApiParam(required=true) CgfySelectInputBean cgfyInput) {
        return AjaxResponse.success(service.select(cgfyInput));
    }

    /**
    * 保存
    * @param input 输入参数
    * @param <#list tableClass.pkFields as field>${field.fieldName}</#list> 主键id
    * @return 输出对象
    */
    @ApiOperation(value = "保存", hidden=true)
    @RequestMapping(value = "<#list tableClass.pkFields as field>/save/{${field.fieldName}}</#list>", method = RequestMethod.POST)
    public AjaxResponse<${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")}> save(@RequestBody  @ApiParam(required=true) ${tableClass.getIntrospectedTable().getAttribute("INPUT_BEAN_SHORT")} input<#list tableClass.pkFields as field>,@PathVariable ${field.shortTypeName} ${field.fieldName}</#list>) {
        return AjaxResponse.success(service.save(input,<#list tableClass.pkFields as field>${field.fieldName}</#list>));
    }

    /**
    * 获取详情
    * @param <#list tableClass.pkFields as field>${field.fieldName}</#list> 主键id
    * @return 输出对象
    */
    @ApiOperation(value = "获取详情", hidden=true)
    @RequestMapping(value = "<#list tableClass.pkFields as field>/{${field.fieldName}}</#list>", method = RequestMethod.GET)
    public AjaxResponse<${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")}> getDetail(<#list tableClass.pkFields as field>@PathVariable ${field.shortTypeName} ${field.fieldName}</#list>) {
        return AjaxResponse.success(service.getDetail(<#list tableClass.pkFields as field>${field.fieldName}</#list>));
    }

    /**
    * 删除
    * @param <#list tableClass.pkFields as field>${field.fieldName}</#list> 主键id
    * @return 输出对象
    */
    @ApiOperation(value = "物理删除", hidden=true)
    @RequestMapping(value="<#list tableClass.pkFields as field>/{${field.fieldName}}</#list>", method = RequestMethod.DELETE)
    public AjaxResponse<Object> deleteForce(<#list tableClass.pkFields as field>@PathVariable ${field.shortTypeName} ${field.fieldName}</#list>) {
        service.deleteForce(<#list tableClass.pkFields as field>${field.fieldName}</#list>);
        return AjaxResponse.success();
    }



	


	

}

