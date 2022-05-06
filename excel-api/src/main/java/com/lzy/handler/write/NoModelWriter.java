package com.lzy.handler.write;

import cn.hutool.core.map.MapUtil;
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

    public static List<List<String>> head(Map<Integer, String> template, List<Integer> indexList) {
        List<List<String>> list = ListUtils.newArrayList();
        for (Map.Entry<Integer, String> entry : template.entrySet()) {
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            if(indexList.contains(entry.getKey())){
                List<String> head0 = ListUtils.newArrayList();
                head0.add(entry.getValue());
                list.add(head0);
            }
        }
        return list;
    }



    public static List<List<Object>> dataList(List<Map<Integer, String>> contents,List<Integer> indexList,Map<Integer, String> filters) {
        if(MapUtil.isEmpty(filters)){
            return dataListNormal(contents, indexList);
        }
        return dataListFilter(contents,indexList,filters);
    }

    /**
     * all
     */
    private static List<List<Object>> dataListNormal(List<Map<Integer, String>> contents, List<Integer> indexList) {
        List<List<Object>> list = ListUtils.newArrayList();
        for(Map<Integer, String> content : contents){
            List<Object> data = ListUtils.newArrayList();
            for (Map.Entry<Integer, String> entry : content.entrySet()) {
//                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                if(indexList.contains(entry.getKey())){
                    data.add(entry.getValue());
                }
            }
            list.add(data);
        }
        return list;
    }

    /**
     * filter
     */
    private static List<List<Object>> dataListFilter(List<Map<Integer, String>> contents, List<Integer> indexList,Map<Integer, String> filters) {
        List<List<Object>> list = ListUtils.newArrayList();
        a:for(Map<Integer, String> content : contents){
            List<Object> dataList = ListUtils.newArrayList();
            for (Map.Entry<Integer, String> entry : content.entrySet()) {
//                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                if(indexList.contains(entry.getKey())){
                    if(filters.containsKey(entry.getKey())){
                        String filter = filters.get(entry.getKey()).trim();
                        String data = entry.getValue();
                        if(filter.equals(data)){
                            dataList.add(entry.getValue());
                        }else {
                            continue a;
                        }
                    }else{
                        dataList.add(entry.getValue());
                    }
                }
            }
            list.add(dataList);
        }
        return list;
    }

}
