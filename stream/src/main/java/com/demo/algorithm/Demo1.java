package com.demo.algorithm;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/13
 * Time: 9:21
 * Description: No Description
 */
public class Demo1 {
    /**
     * 给定一个不会重复数字的数组nums,返回其所有可能的全排列
     * 例如[1,2]
     * 返回[[1,2],[2,1]]
     */
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(Arrays.asList(1,2,3,3));
        //去重
        System.out.println(getAllCompose(nums));
        System.out.println(getComposes(nums));
        List<List<Integer>> subList = subList(nums,0);


        System.out.println(subList);

        List<List<Integer>> result = pairList(subList);
        System.out.println(result);
    }

    /**
     * [2,3,4,5] -> [[2],[3,4,5]]
     * @param list
     * @param index
     * @return
     */
    public static List<List<Integer>> subList(List<Integer> list,int index){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subList = CollectionUtil.sub(list,index,index+1);
        list.removeAll(subList);
        result.add(subList);
        result.add(list);
        return result;
    }

    /**
     * [[1],[2,3]] -> [1,2],[1,3]
     * @param lists
     * @return
     */
    public static List<List<Integer>> pairList(List<List<Integer>> lists){
        List<Integer> numbers1 = lists.get(0);
        List<Integer> numbers2 = lists.get(1);
        return numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> Arrays.asList(i,j)))
                .collect(Collectors.toList());
    }

    /**
     * new ArrayList<>(Arrays.asList(2,3,4,5,4))
     * @param nums
     * @return
     */
    public static List<List<Integer>> getAllCompose(List<Integer> nums){
        //去重
        List<Integer> distinctNums = nums.stream().distinct().collect(Collectors.toList());
        //int -> string
        List<String> list = Convert.toList(String.class,distinctNums);
        Stream<String> stream = list.stream();
        for(int i=0;i<list.size()-1;i++){
            stream = stream
//                    .parallel()
                    .flatMap(str -> list.stream().filter(s -> !str.contains(s)).map(str.concat("-")::concat));
        }
        //[1-2,2-1]
        List<String> strList =  stream.collect(Collectors.toList());
        //[1-2,2-1] -> [[1,2],[2,1]]
        List<List<Integer>> result = strList.stream()
                .map(x ->
                        {
                            int[] a = StrUtil.splitToInt(x,"-");
                            return Convert.toList(Integer.class,a);
                        })
                .collect(Collectors.toList());
        return result;
    }

    /**
     * @param nums new ArrayList<>(Arrays.asList(1,2,3,3))
     * @return [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
     */
    public static List<List<Integer>> getComposes(List<Integer> nums){
        //去重[1,2,3]
        List<Integer> distinctNums = nums.stream().distinct().collect(Collectors.toList());
        //切割[[1], [2], [3]]
        List<List<Integer>> operateList = ListUtil.splitAvg(distinctNums,distinctNums.size());
        Stream<List<Integer>> stream = operateList.stream();
        for(int i=0;i<distinctNums.size()-1;i++){
            stream = stream
                    .flatMap(
                            list1 -> operateList.stream()
                                    .filter(list2 -> !list1.contains(list2.get(0)))
                                    .map(
                                            list2 -> {
                                                List<Integer> list3 = new ArrayList<>();
                                                list3.addAll(list1);
                                                list3.addAll(list2);
                                                return list3;
                                    })
                    );
        }
        return stream.collect(Collectors.toList());
    }

}
