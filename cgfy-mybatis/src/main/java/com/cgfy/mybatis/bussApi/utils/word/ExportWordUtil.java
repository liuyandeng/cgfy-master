package com.cgfy.mybatis.bussApi.utils.word;

import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 导出word工具类
 *
 * @author 刘彦登
 */
public class ExportWordUtil {
    /**
     * 　　* @description: 使bean中为null的属性转换成空字符串
     *      https://blog.csdn.net/weixin_42901057/article/details/90694320
     * 　　* @param [bean]
     * 　　* @return void
     * 　　* @throws
     * 　　* @author TZH
     * 　　* @date 2019/5/30 11:24
     *
     */
    public static <T> void nullToEmpty(T bean) {
        Field[] field = bean.getClass().getDeclaredFields();
        for (int j = 0; j < field.length; j++) {     //遍历所有属性
            String name = field[j].getName();    //获取属性的名字
            //将属性的首字符大写，方便构造get，set方法
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            String type = field[j].getGenericType().toString();    //获取属性的类型
            if (type.equals("class java.lang.String")) {   //如果type是类类型，则前面包含"class "，后面跟类名
                try {
                    Method mGet = bean.getClass().getMethod("get" + name);
                    String value = (String) mGet.invoke(bean);    //调用getter方法获取属性值
                    if (value == null || "".equals(value)) {
                        Method mSet = bean.getClass().getMethod("set" + name, new Class[]{String.class});
                        mSet.invoke(bean, new Object[]{new String("")});
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将图片内容转换成Base64编码的字符串
     *
     * @param imageFile 图片文件的全路径名称
     * @return 转换成Base64编码的图片内容字符串
     */
    public static String getImageBase64String(String imageFile) {
        if (StringUtils.isEmpty(imageFile)) {
            return "";
        }
        File file = new File(imageFile);
        if (!file.exists()) {
            return "";
        }
        InputStream is = null;
        byte[] data = null;
        try {
            is = new FileInputStream(file);
            data = new byte[is.available()];
            is.read(data);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Encoder encoder = Base64.getEncoder();
        String result = data != null ? encoder.encodeToString(data) : "";
        return result;
    }

    /**
     * 将图片流转换成Base64编码的字符串
     *
     * @param is 图片流
     * @return
     */
    public static String getImageBase64String(InputStream is) {
        byte[] data = null;
        try {
            data = new byte[is.available()];
            is.read(data);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Encoder encoder = Base64.getEncoder();
        String result = data != null ? encoder.encodeToString(data) : "";
        return result;
    }


    @SuppressWarnings("resource")
    public static void outDocx(File documentFile, String docxTemplate, String toFilePath) throws
            ZipException, IOException {

        try {
            File docxFile = new File(docxTemplate);
            ZipFile zipFile = new ZipFile(docxFile);
            Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
            ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(toFilePath));
            int len = -1;
            byte[] buffer = new byte[2048];
            while (zipEntrys.hasMoreElements()) {
                ZipEntry next = zipEntrys.nextElement();
                InputStream is = zipFile.getInputStream(next);
                zipout.putNextEntry(new ZipEntry(next.toString()));
                if ("word/document.xml".equals(next.toString())) {
                    InputStream in = new FileInputStream(documentFile);
                    while ((len = in.read(buffer)) != -1) {
                        zipout.write(buffer, 0, len);
                    }
                    in.close();
                } else {
                    while ((len = is.read(buffer)) != -1) {
                        zipout.write(buffer, 0, len);
                    }
                    is.close();
                }
            }
            zipout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * java读取网络上的图片并保存到本地，图片文件名不变
     *
     * @param fileUrl  网络资源地址
     * @param savePath 保存地址
     * @return
     */
    public static String downLoadFromUrl(String fileUrl, String savePath) {
        try {

            //将网络资源地址传给,即赋值给url
            URL url = new URL(fileUrl);
            //此为联系获得网络资源的固定格式用法，以便后面的in变量获得url截取网络资源的输入流
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            DataInputStream in = new DataInputStream(connection.getInputStream());
            //此处也可用BufferedInputStream与BufferedOutputStream
            DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath));
            //将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址
            byte[] buffer = new byte[4096];
            int count = 0;
            //将输入流以字节的形式读取并写入buffer中
            while ((count = in.read(buffer)) > 0) {
                out.write(buffer, 0, count);
            }
            //后面三行为关闭输入输出流以及网络资源的固定格式
            out.close();
            in.close();
            connection.disconnect();
            return savePath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        try {
            // String imgUrl = "http://172.16.4.35:8840/parkFileEx/download/137/118/77490302c7a8400e86cce55bc4bb8b1d.jpg";
            String imgUrl = "http://192.168.0.17:8280/tomcat.png";
            String savePath = "F:/upload/out";
            String fileName = imgUrl.substring(imgUrl.lastIndexOf("/"));//将返回最后一个符号为‘/’后imgUrl变量中的所有字符，包裹此自身符号
            String img = downLoadFromUrl(imgUrl, savePath + fileName);
            System.out.println(img);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
