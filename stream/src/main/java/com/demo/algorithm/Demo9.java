package com.demo.algorithm;

import cn.hutool.core.collection.ListUtil;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/6/20
 * Time: 10:02
 * Description: 冒泡排序
 */
public class Demo9 {
    public static void main(String[] args) {
        int[] a = new int[]{1,3,5,2,5,55,2};
        bubbleSort(a);
        System.out.println(Arrays.stream(a).boxed().collect(Collectors.toList()));
    }

    public static int[] bubbleSort(int[] arr){
        if(arr == null || arr.length < 2){
            return arr;
        }
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;

        //无序数组的边界，每次比较只需要比到这里为止
        int sortBorder = arr.length - 1;

        for (int i = 0; i < arr.length - 1; i++){
            boolean isSort = true;
            for (int j = 0 ; j < sortBorder ; j ++){
                if(arr[j+1] < arr[j] ){
                    isSort = false;
                    int t = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = t;
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            //一趟下来是否发生位置交换，没有则跳出大循环
            if(isSort) break;
        }
        return arr;
    }

}
