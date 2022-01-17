package com.demo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/17
 * Time: 15:54
 * Description: 去除一个最大值，一个最小值 然后求平均值
 */
public class Demo4 {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(Arrays.asList(1,3,5,-1,0,9,8,5));
        List<Integer> sortNums = nums.stream()
                .parallel()
                .sorted(Integer::compare)
                .collect(Collectors.toList());
        System.out.println("sortList:"+sortNums);
        sortNums.remove(0);
        sortNums.remove(sortNums.size()-1);
        System.out.println("operateList:"+sortNums);
        System.out.println(sortNums);
        double result = sortNums.stream()
                .mapToInt(x -> x)
                .average()
                .getAsDouble();
        System.out.println("result:"+result);
    }
}
