package com.lzy.service;


import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.lzy.constant.Cnst;
import com.lzy.handler.read.HeadDataListener;
import com.lzy.handler.read.NoModelDataListener;
import com.lzy.handler.write.NoModelWriter;
import com.lzy.util.NumUtil;
import com.lzy.util.ProjectInfoPropertyUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/5
 * Time: 11:04
 * Description: No Description
 */
public class Logic {
    public static void main(String[] args){
        String filePath = ProjectInfoPropertyUtil.getProperty("FilePath")
                + File.separator;

        String fileName = ProjectInfoPropertyUtil.getProperty("FileName");

        //读文件
        String readFileName = filePath + fileName;
        //写文件
        String writeFileName = filePath + fileName + "-"
                + System.currentTimeMillis() +".xlsx";
        //过滤列数
        String filterColumnStr = ProjectInfoPropertyUtil.getProperty("FilterColumn");

        if(StringUtils.isBlank(filterColumnStr)){
            throw new RuntimeException("FilterColumn 配置不能为空");
        }
        List<Integer> indexList = new ArrayList<>();

        List<String> letters = StrUtil.split(filterColumnStr,"|");
        indexList = letters.stream()
                .map(
                        letter -> {
                            return NumUtil.letterToNumber(letter) - 1;
                        }
                )
                .collect(Collectors.toList());

        //过滤条件
        String filterDetailStr = ProjectInfoPropertyUtil.getProperty("FilterDetail");
        Map<Integer, String> filterDetailMap = new HashMap<>();
        if(StringUtils.isBlank(filterDetailStr)){
            filterDetailStr = Cnst.DEFAULT_FILTER_DETAIL;
        }
        if(!filterDetailStr.equals(Cnst.DEFAULT_FILTER_DETAIL)){
            //str->map
            List<String> details = StrUtil.split(filterDetailStr,"|");
            filterDetailMap = details.stream()
                    .collect(Collectors.toMap(
                       x->NumUtil.letterToNumber(StrUtil.split(x,":").get(0))-1,
                            y->StrUtil.split(y,":").get(1)
                    ));
        }


        Map<Integer,String> header = new HashMap<>();
        // 读取消息头
        EasyExcel.read(readFileName, new HeadDataListener(
                head -> {
                    header.putAll(head);
                }
        )).sheet().doRead();

        // 文件表头 格式转换
        List<List<String>> writeHead = NoModelWriter.head(header,indexList);

        List<Map<Integer, String>> contents = new ArrayList<>();
        // 这里 只要，然后读取第一个sheet 同步读取会自动finish
        EasyExcel.read(readFileName, new NoModelDataListener(
                    data -> {
                        contents.addAll(data);
                    }
                )).sheet().doRead();

        //文件内容 格式转换
        List<List<Object>> writeContent = NoModelWriter.dataList(contents,indexList,filterDetailMap);


        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        //设置自定义 字体模板
        // HorizontalCellStyleStrategy 每一行的样式都一样 或者隔行一样
        // AbstractVerticalCellStyleStrategy 每一列的样式都一样 需要自己回调每一页
        // LongestMatchColumnWidthStyleStrategy 自适应策略
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)10);
        headWriteFont.setFontName("Arial");
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
//        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
//        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short)10);
        contentWriteFont.setFontName("Arial");
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
        ExcelWriter excelWriter = EasyExcel.write(writeFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("sheet1")
                        .head(writeHead)
                        .registerWriteHandler(horizontalCellStyleStrategy)
                        .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                        .build();

        try {
            excelWriter.write(writeContent,writeSheet);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }

//        EasyExcel.write(writeFileName)
//                .head(writeHead)
//                .registerWriteHandler(horizontalCellStyleStrategy)
//                .sheet("Sheet1")
//                .doWrite(writeContent);
    }

}
