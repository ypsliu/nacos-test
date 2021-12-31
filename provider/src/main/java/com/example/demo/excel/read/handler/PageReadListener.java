package com.example.demo.excel.read.handler;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


/**
 * @author lz
 * @date 2021-10-18 14:01
 */
@Slf4j
public class PageReadListener<T> implements ReadListener<T> {
    /**
     * Single handle the amount of data
     */
    public static final int BATCH_COUNT = 1000;
    /**
     * Temporary storage of data
     */
    private List<T> cachedData = new ArrayList<>(BATCH_COUNT);
    /**
     * consumer
     */
    private final Consumer<List<T>> consumer;

    public PageReadListener(Consumer<List<T>> consumer) {
        this.consumer = consumer;
    }


    @Override
    public void invoke(T data, AnalysisContext context) {
        cachedData.add(data);
        log.info("add list success:"+data);
        if (cachedData.size() >= BATCH_COUNT) {
            consumer.accept(cachedData);
            // 存储完成清理 list
            cachedData.clear();
        }
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        consumer.accept(cachedData);
    }


}
