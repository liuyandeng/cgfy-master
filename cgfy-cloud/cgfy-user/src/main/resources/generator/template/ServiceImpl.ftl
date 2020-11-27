package ${package};

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import ${props.fwPackage}.base.exception.BusinessException;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import ${props.fwPackage}.base.service.impl.BaseServiceImpl;
import ${props.fwPackage}.base.bean.SelectInputBean;
import ${props.fwPackage}.base.bean.CgfyListResponse;
import ${props.fwPackage}.base.bean.CgfySelectInputBean;
import ${props.fwPackage}.base.bean.BeanTrans;
import ${props.fwPackage}.base.bean.select.Condition;
import ${props.fwPackage}.base.bean.select.Direction;
import ${props.fwPackage}.base.bean.select.Order;
import ${props.fwPackage}.base.bean.BaseSelectField;
import ${props.fwPackage}.base.domain.mapper.BaseMapper;
import ${props.fwPackage}.base.domain.model.BaseModel;
import ${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN")};
import ${tableClass.getIntrospectedTable().getAttribute("SERVICE_INTERFACE")};
import ${tableClass.getIntrospectedTable().getAttribute("INPUT_BEAN")};
import ${tableClass.getIntrospectedTable().getBaseRecordType()};

/**
 * 「${tableClass.introspectedTable.remarks}」基础Service
 *
 * @author ${props.author}
 */
@Service("${tableClass.getIntrospectedTable().getAttribute("SERVICE_INTERFACE_SHORT")}")
public class ${tableClass.getIntrospectedTable().getAttribute("SERVICE_INTERFACE_IMPL_SHORT")} extends BaseServiceImpl<${tableClass.shortClassName},${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")}> implements ${tableClass.getIntrospectedTable().getAttribute("SERVICE_INTERFACE_SHORT")}{

    /**
     * Mapper
     */
    @Resource
    private ${tableClass.getIntrospectedTable().getMyBatis3JavaMapperType()} mapper;



    /**
    * 检索
    * @param cgfyInput 输入参数
    * @return 输出对象
    */
    public CgfyListResponse<${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")}> select(CgfySelectInputBean cgfyInput) {
         SelectInputBean input = BeanTrans.zgcfzBeanToNormal(cgfyInput);
         Example example = new Example(${tableClass.shortClassName}.class);
        // 检索项目设定
        if (input.getFields() != null && input.getFields().size() != 0) {
            for (String field : input.getFields()) {
                example = example.selectProperties(field);
            }
       }

        // 检索条件变换
        if (input.getCondition() != null && input.getCondition().size() != 0) {
            Criteria criteria = example.createCriteria();
            for (Condition item : input.getCondition()) {
                item.addCondition(example, criteria, ${tableClass.shortClassName}.class);
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

        CgfyListResponse<${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")}> result = new CgfyListResponse<${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")}>();
        result.setTotalCount(mapper.selectCountByExample(example));
        List<${tableClass.shortClassName}> resultData = null;
        if(input.getRowBounds() == null) {
            resultData = mapper.selectByExample(example);
        } else {
            resultData = mapper.selectByExampleAndRowBounds(example, input.getRowBounds());
            result.setPage(input.getRowBounds().getOffset());
            result.setPageSize(input.getRowBounds().getLimit());
            result.setPageCount((int) Math.ceil(result.getTotalCount()/result.getPageSize()));
        }
        List<${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")}> resultRecords = new ArrayList<${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")}>();
        for(${tableClass.shortClassName} item : resultData){
            ${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")} out = new ${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")}();
            BeanUtils.copyProperties(item,out);
            resultRecords.add(out);
        }
        result.setForms(resultRecords);
        return result;
    }

    /**
    * 保存
    * @param input 输入参数
    * @param <#list tableClass.pkFields as field>${field.fieldName}</#list> 主键id
    * @return 输出对象
    */
    @Transactional(rollbackFor = Exception.class)
    public ${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")} save(${tableClass.getIntrospectedTable().getAttribute("INPUT_BEAN_SHORT")} input<#list tableClass.pkFields as field>,${field.shortTypeName} ${field.fieldName}</#list>) {
        if (StringUtils.isBlank(<#list tableClass.pkFields as field>${field.fieldName}</#list>)) {
            throw new BusinessException("主键不能为空");
        }
        ${tableClass.shortClassName} record = mapper.selectByPrimaryKey(<#list tableClass.pkFields as field>${field.fieldName}</#list>);
        if (record==null) {//新增
            ${tableClass.shortClassName} entity = new ${tableClass.shortClassName}();
            BeanUtils.copyProperties(input,entity);
            <#list tableClass.pkFields as field>
            entity.set${field.fieldName?cap_first}(${field.fieldName});// 主键
            </#list>
            int count = mapper.insert(entity);
            if (count != 1) {
                throw new BusinessException("${tableClass.shortClassName}插入失败");
            }
        } else {//更新
            BeanUtils.copyProperties(input,record);
            <#list tableClass.pkFields as field>
            record.set${field.fieldName?cap_first}(${field.fieldName});// 主键
            </#list>
            int count = mapper.updateByPrimaryKeySelective(record);
            if (count != 1) {
                throw new BusinessException("${tableClass.shortClassName}更新失败");
            }

        }

        <#if (tableClass.pkFields?size > 1)>
        //联合主键
        java.util.Map<String, String> primaryKeyMap = new java.util.HashMap<String,String>();
        <#list tableClass.pkFields as field>
        primaryKeyMap.put("${field.fieldName}", ${field.fieldName});
        </#list>
        ${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")} out=new ${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")}(mapper.selectByPrimaryKey(primaryKeyMap));
        return out;
        <#else>
        return getDetail(<#list tableClass.pkFields as field>${field.fieldName}</#list>);
        </#if>
    }

    /**
    * 获取详情
    * @param <#list tableClass.pkFields as field>${field.fieldName}</#list> 主键id
    * @return 输出对象
    */
    public ${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")} getDetail(<#list tableClass.pkFields as field>${field.shortTypeName} ${field.fieldName}</#list>) {
        if (StringUtils.isBlank(<#list tableClass.pkFields as field>${field.fieldName}</#list>)) {
            throw new BusinessException("主键不能为空");
        }
        ${tableClass.shortClassName} record =mapper.selectByPrimaryKey(<#list tableClass.pkFields as field>${field.fieldName}</#list>);
        ${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")} out=new ${tableClass.getIntrospectedTable().getAttribute("RECORD_OUTPUT_BEAN_SHORT")}(record);
        return out;
    }

    /**
    * 删除
    * @param <#list tableClass.pkFields as field>${field.fieldName}</#list> 主键id
    * @return 输出对象
    */
    @Transactional(rollbackFor = Exception.class)
    public void deleteForce(<#list tableClass.pkFields as field>${field.shortTypeName} ${field.fieldName}</#list>) {
        int count = mapper.deleteByPrimaryKey(<#list tableClass.pkFields as field>${field.fieldName}</#list>);
        if (count != 1) {
            throw new BusinessException("${tableClass.shortClassName}删除失败");
        }

    }


    /**
    * 获取mapper
    */
    @Override
    public BaseMapper getBusinessMapper() {
        return this.mapper;
    }




}

