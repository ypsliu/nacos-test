package com.lzy.handler.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.util.ConverterUtils;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/5
 * Time: 13:13
 * Description: No Description
 */
public class HeadDataListener extends AnalysisEventListener<Map<Integer, String>> {

    private final Consumer<Map<Integer, String>> consumer;


    public HeadDataListener(Consumer<Map<Integer, String>> consumer) {
        this.consumer = consumer;
    }

    /**
     * 这里会一行行的返回头
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        Map<Integer, String> header = ConverterUtils.convertToStringMap(headMap, context);
        System.out.println("解析到一条头数据:"+ header);
        consumer.accept(header);
        // 如果想转成成 Map<Integer,String>
        // 方案1： 不要implements ReadListener 而是 extends AnalysisEventListener
        // 方案2： 调用 ConverterUtils.convertToStringMap(headMap, context) 自动会转换
    }

    @Override
    public void invoke(Map<Integer, String> integerStringMap, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
