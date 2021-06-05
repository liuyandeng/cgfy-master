package com.cgfy.mybatis.bussApi.utils.excel.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.converters.url.UrlImageConverter;
import lombok.Data;

import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * title:
 * Description：TODO
 * Author: Administrator
 * Date: 2021/6/4
 */
@Data
@ColumnWidth(30)//列宽,可具体到某个字段上
@HeadRowHeight(30)//表头行高
@ContentRowHeight(20)//数据行行高
public class WriteData {
    //index如果不设置,列名的先后顺序将按照字段从上到下排序,index从0开始,如果是数据字段字典,可以指定converter进行转换
    @ExcelProperty(value = "字符串标题")
    private String stringData;//STRING
    @ExcelProperty(value = {"多表头","日期标题"})
    private Date dateData;//STRING
    @ExcelProperty(value = {"多表头","数字标题"})
    private Double doubleData;//NUMBER
    @ExcelProperty(value = "图片标题",index = 3)
    private URL imgUrl;//IMAGE


    /**
     * 忽略这个字段
     * 正常情况下,这个类有多少个字段就有多少列,没有设置标题的话默认是字段名称,加上@ExcelIgnore,不算进列
     */
    @ExcelIgnore
    private String ignore;

}
