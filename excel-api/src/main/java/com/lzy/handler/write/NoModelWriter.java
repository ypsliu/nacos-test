package com.lzy.handler.write;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.lzy.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/5
 * Time: 13:54
 * Description: 不创建对象的写
 */
public class NoModelWriter {

    public static List<List<String>> head(Map<Integer,String> template,List<Integer> indexList) {
        List<List<String>> list = ListUtils.newArrayList();
        for (Map.Entry<Integer, String> entry : template.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            List<String> head0 = ListUtils.newArrayList();
            head0.add(entry.getValue());
            list.add(head0);
        }
        return list;
    }

    public static List<List<Object>> dataList(List<Map<Integer, String>> contents,List<Integer> indexList) {
        List<List<Object>> list = ListUtils.newArrayList();
        for(Map<Integer, String> content : contents){
            List<Object> data = ListUtils.newArrayList();
            for (Map.Entry<Integer, String> entry : content.entrySet()) {
                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                data.add(entry.getValue());
            }
            list.add(data);
        }
        return list;
    }

}
