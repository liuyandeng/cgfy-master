package com.cgfy.mybatis.bussApi.utils.excel;

import com.alibaba.excel.EasyExcel;;
import com.cgfy.mybatis.bussApi.utils.DateUtil;
import com.cgfy.mybatis.bussApi.utils.excel.bean.FillData;
import com.cgfy.mybatis.bussApi.utils.excel.handler.MyCellWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 填充Excel
 * 官网地址: https://www.yuque.com/easyexcel/doc/fill
 */
@Slf4j
public class FillTest {
    private static final Logger log = LoggerFactory.getLogger(FillTest.class);

    /**
     * 读取excel
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String templateFileName =  "E:/upload/ExcelFill.xlsx";
        File file = new File(templateFileName);
        if (!file.exists()) {//不存在这个文件
            System.out.println("模板文件不存在");
            InputStream inputStream = FillTest.class.getClassLoader().getResourceAsStream("templates/ExcelFill.xlsx");
            int index;
            byte[] bytes = new byte[1024];
            FileOutputStream downloadFile = new FileOutputStream(templateFileName);
            while ((index = inputStream.read(bytes)) != -1) {
                downloadFile.write(bytes, 0, index);
                downloadFile.flush();
            }
            downloadFile.close();
            inputStream.close();
        }
        List<FillData> list = new ArrayList<FillData>();
        for(int i=1;i<=5;i++){
            FillData fillData = new FillData();
            fillData.setTest1("姓名"+i);
            fillData.setTest2("性别"+i);
            fillData.setTest3("年龄"+i);
            fillData.setTest4("手机号"+i);
            fillData.setTest5("学历"+i);
            fillData.setTest6("家庭住址"+i);
            fillData.setImgUrl( new URL("http://file.fangzuobiao.com:9000/5,04e1833f2957e9.jpg"));
            list.add(fillData);
        }
        String currentDate = DateUtil.getStringDateNum();//当前时间
        String fileName =  "Excel填充案例" +currentDate+".xlsx" ;
        String url = "http://localhost:8080/" + fileName;
        System.out.println(url);
        try {
            EasyExcel.write("E:/upload/" +fileName).withTemplate(templateFileName).sheet().doFill(list);
        }catch (Exception e){
            File errorFile=new File("E:/upload/" +fileName);
            errorFile.delete();
            e.printStackTrace();
        }

    }


}