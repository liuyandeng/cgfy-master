package com.cgfy.mybatis.bussApi.utils.excel.converters;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.IoUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * title:easyexcel导出图片
 * Description：TODO
 * Author: Administrator
 * Date: 2021/5/28
 */
public class MyUrlImageConverter implements Converter<URL> {
    @Override
    public Class supportJavaTypeKey() {
        return URL.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.IMAGE;
    }

    @Override
    public URL convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                 GlobalConfiguration globalConfiguration) {
        throw new UnsupportedOperationException("Cannot convert images to url.");
    }

    @Override
    public CellData convertToExcelData(URL value, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) throws IOException {
        InputStream inputStream = null;
        try {
            //开启连接
            URLConnection uc = value.openConnection();
            URL url  = null;
            //获取响应状态
            int statusCode = ((HttpURLConnection) uc).getResponseCode();
            switch (statusCode){
                case 200:
                    inputStream = value.openStream();
                    break;
                case 404:
                    //默认给一个图片
                    url = new URL("http://file.fangzuobiao.com:9000/6,04d9da4d9c3156");
                    inputStream = url.openStream();
                    break;
                default :
                    url = new URL("http://file.fangzuobiao.com:9000/6,04d9da4d9c3156");
                    inputStream = url.openStream();
                    break;
            }
            byte[] bytes = IoUtils.toByteArray(inputStream);
            return new CellData(bytes);
        }catch (ConnectException exception){
            //捕获下链接异常
            URL url = new URL("http://file.fangzuobiao.com:9000/6,04d9da4d9c3156");
            inputStream = url.openStream();
            byte[] bytes = IoUtils.toByteArray(inputStream);
            return new CellData(bytes);
        }catch (FileNotFoundException fileNotFoundException){
            URL url = new URL("http://file.fangzuobiao.com:9000/6,04d9da4d9c3156");
            inputStream = url.openStream();
            byte[] bytes = IoUtils.toByteArray(inputStream);
            return new CellData(bytes);
        }finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
