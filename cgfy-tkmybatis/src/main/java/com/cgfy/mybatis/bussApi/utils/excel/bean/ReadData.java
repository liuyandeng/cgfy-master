package com.cgfy.mybatis.bussApi.utils.excel.bean;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ReadData {
    @ExcelProperty(index = 4)
    private String name;
    @ExcelProperty(index = 5)
    private String code;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}