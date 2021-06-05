package com.cgfy.mybatis.bussApi.utils.excel;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cgfy.mybatis.bussApi.utils.excel.bean.WriteData;
import com.cgfy.mybatis.bussApi.utils.excel.handler.MyCellWriteHandler;

import com.alibaba.excel.EasyExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * title:
 * Description：TODO
 * Author: Administrator
 * Date: 2021/6/4
 */
public class WriteTest {
    private static final Logger log = LoggerFactory.getLogger(WriteTest.class);
    /**
     * 最简单的写
     * <p>
     * 1. 创建excel对应的实体对象
     * <p>
     * 2. 直接写即可
     */
    public static void main(String[] args) throws MalformedURLException {
        List<WriteData> list = new ArrayList<WriteData>();
        for (int i = 0; i < 10; i++) {
            WriteData data = new WriteData();
            data.setStringData("字符串" + i);
            data.setDateData(new Date());
            data.setDoubleData(0.56);
            data.setImgUrl(new URL("http://file.fangzuobiao.com:9000/5,04e1833f2957e9.jpg"));
            list.add(data);
        }

        // 写法1
        String fileName =  "E:/upload/simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
       // EasyExcel.write(fileName, WriteData.class).sheet("模板").doWrite(list);
        EasyExcel.write(fileName, WriteData.class).registerWriteHandler(new MyCellWriteHandler()).sheet("Excel导出测试").doWrite(list);

    }


}
