package com.cgfy.mybatis.bussApi.utils.word;

import com.cgfy.mybatis.bussApi.utils.DateUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * xml形式导出word
 * @author 刘彦登
 */
public class ExportWordByFtl {
    private static final String fp = "F:/upload/out/";
    private static final String fpurl = "http://localhost:8080";
    /**
     * word文件另存为xml形式的导出只能输出doc文件,docx文件打不开,但能输出图片(使用office编辑word,不要使用wps)
     * docx文件改成zip,更改word/document.xml的这种形式导出,不能导出图片(使用office编辑word,不要使用wps)
     *
     * @param xmlTemplateName 模板文件名
     * @param xmlTemplateName 输出文件名
     * @param dataMap         需要填充的数据
     * @param outPath         输出路径
     * @throws Exception
     */
    public static String generateDocWord(String xmlTemplateName, String fileName, Map<String, Object> dataMap, String outPath) throws Exception {
        // 设置FreeMarker的版本和编码格式
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        configuration.setDefaultEncoding("UTF-8");
        // 设置FreeMarker生成Word文档所需要的模板的路径,参考https://www.cnblogs.com/fangjian0423/p/freemarker-templateloading-question.html
        configuration.setClassForTemplateLoading(ExportWordByFtl.class, "/templates");//放在springboot项目下的resources/templates文件夹下
        Template template = configuration.getTemplate(xmlTemplateName);
        template.setOutputEncoding("UTF-8");
        // 输出文档路径及名称
        File outFile = new File(outPath + fileName);
        String url = fpurl + "/" + "out/" + fileName;
        System.out.println(url);
        // 创建一个Word文档的输出流
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
        //FreeMarker使用Word模板和数据生成Word文档
        template.process(dataMap, out);
        out.flush();
        out.close();
        return fileName;
    }


    public static void main(String[] args) throws Exception {
        Map<String, Object> testmap = new HashMap();
        testmap.put("name", "李四");
        testmap.put("age", "20");
        testmap.put("sex", "男");
        testmap.put("folk", "汉");
        testmap.put("birthday", "2000-01-01");
        testmap.put("phone", "18201202293");
       // String imgUrl = ExportWordUtil.downLoadFromUrl("http://192.168.0.17:8280/tomcat.png", fp + "tomcat.png");//网络获取方式
        String imgUrl = fp + File.separator + "head.png";
        File file = new File(imgUrl);
        if (!file.exists()) {//不存在这个文件
            System.out.println("模板文件不存在");
            InputStream inputStream = ExportWordByFtl.class.getClassLoader().getResourceAsStream("templates/head.png");
            int index;
            byte[] bytes = new byte[1024];
            FileOutputStream downloadFile = new FileOutputStream(imgUrl);
            while ((index = inputStream.read(bytes)) != -1) {
                downloadFile.write(bytes, 0, index);
                downloadFile.flush();
            }
            downloadFile.close();
            inputStream.close();
        }
        InputStream is = new FileInputStream(new File(imgUrl));//头像路径
        testmap.put("imgStr", ExportWordUtil.getImageBase64String(is));
        List<Map<String, Object>> eduList = new ArrayList<>();
        Map<String, Object> eduMap1 = new HashMap();
        eduMap1.put("startTime", "2010-09-01");
        eduMap1.put("endTime", "2014-06-30");
        eduMap1.put("school", "清华大学");
        eduMap1.put("education", "本科");
        eduList.add(eduMap1);
        Map<String, Object> eduMap2 = new HashMap();
        eduMap2.put("startTime", "2014-09-01");
        eduMap2.put("endTime", "2016-06-30");
        eduMap2.put("school", "北京大学");
        eduMap2.put("education", "硕士研究生");
        eduList.add(eduMap2);
        testmap.put("eduList", eduList);
        String currentDate = DateUtil.getStringDateNum();//当前时间
        generateDocWord("WordExport.xml", "导出word案例"+currentDate+".doc", testmap, fp);//fm的数据处理

    }
}

