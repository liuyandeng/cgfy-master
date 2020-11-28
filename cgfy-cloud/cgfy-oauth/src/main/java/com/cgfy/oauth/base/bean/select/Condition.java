package com.cgfy.oauth.base.bean.select;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.cgfy.oauth.base.bean.select.condition.*;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.time.DateUtils;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import java.text.ParseException;
import java.util.Date;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Equal.class, name="EQ"), 
    @JsonSubTypes.Type(value = NotEqual.class, name="NE"), 
    @JsonSubTypes.Type(value = GreatThan.class, name="GT"), 
    @JsonSubTypes.Type(value = GreatEqual.class, name="GE"), 
    @JsonSubTypes.Type(value = LessThan.class, name="LT"), 
    @JsonSubTypes.Type(value = LessEqual.class, name="LE"), 
    @JsonSubTypes.Type(value = BetweenAnd.class, name="BETWEEN"),
    @JsonSubTypes.Type(value = Like.class, name="LIKE"), 
    @JsonSubTypes.Type(value = EnumOr.class, name="ENUM")})
@ApiModel(description="例：\r\n" + 
		"　{ \r\n" + 
		"　　property : \"属性名\",\r\n" + 
		"　　value : \"值\",\r\n" + 
		"　　type : \"类型\"\r\n" + 
		"　}\r\n" + 
		"注：\r\n" + 
		"1.类型：EQ(相等),NE(不等),GT(大于),GE(大于等于),LT(小于),LE(小于等于),BETWEEN,LIKE,ENUM\r\n" + 
		"2.类型为BETWEEN和ENUM时，value字段为数组，其余类型value字段为单值")
public abstract class Condition {
    
    private static String DATA_FORMAT = "yyyyMMddHHmmss";
    
    public abstract String getProperty();
    
    public abstract void addCondition(Example example, Criteria criteria, Class clazz);
    
    /**
     * 值取得
     * 
     * @param field 项目
     * @param value 值
     * @param clazz 类型
     * @return
     */
    public Object getValue(String value, Class clazz) {
        EntityTable table = EntityHelper.getEntityTable(clazz);
        EntityColumn colume = table.getPropertyMap().get(getProperty());

        if (colume.getJavaType() == Date.class) {
            try {
                return DateUtils.parseDateStrictly(value, DATA_FORMAT);
            } catch (ParseException e) {
                throw new RuntimeException("日期格式转换错误", e);
            }
        }
        return value;
    }
}
