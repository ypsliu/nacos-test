package com.lzy.handler.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/5
 * Time: 11:02
 * Description: No Description
 */
public class NoModelDataListener extends AnalysisEventListener<Map<Integer, String>> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    private List<Map<Integer, String>> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private final Consumer<List<Map<Integer, String>>> consumer;


    public NoModelDataListener(Consumer<List<Map<Integer, String>>> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        System.out.println("解析到一条数据:"+ JSON.toJSONString(data));
        cachedDataList.add(data);
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        System.out.println("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        System.out.println(cachedDataList.size()+"条数据，开始存储数据库！");
        consumer.accept(cachedDataList);
        System.out.println("存储数据库成功！");
    }
}
