package com.cgfy.mybatis.bussApi.utils.excel;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.cgfy.mybatis.bussApi.utils.excel.bean.ReadData;
import com.cgfy.mybatis.bussApi.utils.word.ExportWordByFtl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 读Excel
 * 官网地址: https://www.yuque.com/easyexcel/doc/easyexcel
 * 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
 */
@Slf4j
public class ReadTest extends AnalysisEventListener<ReadData> {
    private static final Logger log = LoggerFactory.getLogger(ReadTest.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 200;
    List<ReadData> list = new ArrayList<ReadData>();
    /**
     * 这个每一条数据解析都会来调用
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ReadData data, AnalysisContext context) {
        Integer rowNum=context.readRowHolder().getRowIndex();
        log.info("第"+(rowNum+1)+"行解析的数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }
    /**
     * 所有数据解析完成了 都会来调用
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }
   //加上存储数据库
    private void saveData() {
        // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
        log.info("{}条数据，开始存储数据库！", list.size());
        log.info("存储数据库成功！");
    }


    /**
     * 读取excel
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String fileName =  "F:/upload/ExcelRead.xlsx";
        File file = new File(fileName);
        if (!file.exists()) {//不存在这个文件
            System.out.println("模板文件不存在");
            InputStream inputStream = ExportWordByFtl.class.getClassLoader().getResourceAsStream("templates/ExcelRead.xlsx");
            int index;
            byte[] bytes = new byte[1024];
            FileOutputStream downloadFile = new FileOutputStream(fileName);
            while ((index = inputStream.read(bytes)) != -1) {
                downloadFile.write(bytes, 0, index);
                downloadFile.flush();
            }
            downloadFile.close();
            inputStream.close();
        }
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ReadData.class, new ReadTest()).sheet().doRead();
    }


}