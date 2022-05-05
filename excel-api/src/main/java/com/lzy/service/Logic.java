package com.lzy.service;


import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import com.lzy.constant.Cnst;
import com.lzy.handler.read.HeadDataListener;
import com.lzy.handler.read.NoModelDataListener;
import com.lzy.handler.write.NoModelWriter;
import com.lzy.util.NumUtil;
import com.lzy.util.ProjectInfoPropertyUtil;

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

        if(StringUtils.isBlank(filterDetailStr)){
            filterDetailStr = Cnst.DEFAULT_FILTER_DETAIL;
        }
        if(filterDetailStr.equals(Cnst.DEFAULT_FILTER_DETAIL)){

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
        List<List<Object>> writeContent = NoModelWriter.dataList(contents,indexList);


        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(writeFileName).head(writeHead).sheet("Sheet1").doWrite(writeContent);
    }

}
