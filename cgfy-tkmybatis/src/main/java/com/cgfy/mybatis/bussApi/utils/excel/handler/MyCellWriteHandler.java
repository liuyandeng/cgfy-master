package com.cgfy.mybatis.bussApi.utils.excel.handler;

import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * 测试多张图片放到一个单元格里
 */
public class MyCellWriteHandler implements CellWriteHandler {
    /**
     * 在创建单元格之前调用
     *
     * @param writeSheetHolder
     * @param writeTableHolder
     * @param row
     * @param head
     * @param columnIndex
     * @param relativeRowIndex
     * @param isHead
     */
    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder,
                                 WriteTableHolder writeTableHolder, Row row,
                                 Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {

    }

    /**
     * 创建单元格后调用
     *
     * @param writeSheetHolder
     * @param writeTableHolder
     * @param cell
     * @param head
     * @param relativeRowIndex
     * @param isHead
     */
    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder,
                                WriteTableHolder writeTableHolder, Cell cell, Head head,
                                Integer relativeRowIndex, Boolean isHead) {

    }

    /**
     * 单元格数据转换后调用
     *
     * @param writeSheetHolder
     * @param writeTableHolder
     * @param cellData
     * @param cell
     * @param head
     * @param relativeRowIndex 行索引,最大数是list的size-1
     * @param isHead
     */
    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder,
                                       WriteTableHolder writeTableHolder, CellData cellData,
                                       Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {

        //  在 数据转换成功后 ，修改第一列 当然这里也可以根据其他判断  然后不是头  就把类型设置成空 这样easyexcel 不会去处理该单元格
        if (isHead || head.getColumnIndex() !=3) {
            return;
        }
        cellData.setType(CellDataTypeEnum.EMPTY);

    }

    /**
     * 在单元上的所有操作完成后调用
     *
     * @param writeSheetHolder
     * @param writeTableHolder
     * @param cellDataList
     * @param cell
     * @param head
     * @param relativeRowIndex 行索引,最大数是list的size-1
     * @param isHead
     */
    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder,
                                 WriteTableHolder writeTableHolder, List<CellData> cellDataList, Cell cell,
                                 Head head, Integer relativeRowIndex, Boolean isHead) {
        System.out.println("name:"+cell);
        //  在 单元格写入完毕后 ，自己填充图片
        if (head.getColumnIndex() !=3|| isHead || cellDataList.isEmpty()) {
            return;
        }
        System.out.println("type:"+cellDataList.get(0).getType().name());
        Sheet sheet = cell.getSheet();
        // cellDataList 是list的原因是 填充的情况下 可能会多个写到一个单元格 但是如果普通写入 一定只有一个
       // if("IMAGE".equals(cellDataList.get(0).getType().name())){
            byte[] imageValue=cellDataList.get(0).getImageValue();
           // imageValue = IOUtils.toByteArray(new FileInputStream("D:\\Documents\\Pictures\\jiucai.jpg"));//本地图片
            int index = sheet.getWorkbook().addPicture(imageValue, HSSFWorkbook.PICTURE_TYPE_JPEG);
            Drawing drawing = sheet.getDrawingPatriarch();
            if (drawing == null) {
                drawing = sheet.createDrawingPatriarch();
            }
            CreationHelper helper = sheet.getWorkbook().getCreationHelper();
            ClientAnchor anchor = helper.createClientAnchor();
            // 设置图片坐标
            anchor.setDx1(0);
            anchor.setDx2(0);
            anchor.setDy1(0);
            anchor.setDy2(0);
            //设置图片位置
            anchor.setCol1(cell.getColumnIndex());
            anchor.setCol2(cell.getColumnIndex() + 1);
            anchor.setRow1(cell.getRowIndex());
            anchor.setRow2(cell.getRowIndex() + 1);
            // 设置图片可以随着单元格移动
            anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
            drawing.createPicture(anchor, index);
        }

   // }
}