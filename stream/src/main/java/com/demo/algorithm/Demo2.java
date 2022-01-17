package com.demo.algorithm;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/14
 * Time: 10:20
 * Description: 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 */
public class Demo2 {
    public static void main(String[] args) {
        Long beginTime = System.currentTimeMillis();
        List<Integer> nums = new ArrayList<>(Arrays.asList(1,3,-1,-3,5,3,6,7));
        int offset = 4;
        //切割，求最大值
        List<Integer> result = new ArrayList<>();
        if(offset > nums.size()){
            Integer max = nums.stream()
                    .max(Integer::compare)
                    .orElse(Integer.MIN_VALUE);
            result.add(max);
        }else{
            for (int i=0;i<=nums.size()-offset;i++){
                Integer max = getSplitListMax(nums,i,offset);
                result.add(max);
            }
        }
        System.out.println("result:"+result);
        System.out.println("use time:"+(System.currentTimeMillis()-beginTime));
    }

    /**
     *
     * @param nums
     * @param begin 起始下标
     * @param offset 偏移量
     * @return
     */
    public static Integer getSplitListMax(List<Integer> nums,int begin,int offset){
        int end = begin+(offset-1);
        List<Integer> split = new ArrayList<>();
        for (int i = begin ;i<=end ;i++){
            split.add(nums.get(i));
        }
        System.out.println("split:"+split);
        Integer max = split.stream()
                .max(Integer::compareTo)
                .orElse(Integer.MIN_VALUE);
        return max;
    }
}
