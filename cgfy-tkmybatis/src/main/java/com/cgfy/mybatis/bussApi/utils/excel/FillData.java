package com.cgfy.mybatis.bussApi.utils.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.url.UrlImageConverter;
import lombok.Data;

import java.net.URL;

@Data
public class FillData {
    private String test1;
    private String test2;
    private String test3;
    private String test4;
    private String test5;
    private String test6;
    @ExcelProperty(converter = UrlImageConverter.class)
    private URL imgUrl;
}
