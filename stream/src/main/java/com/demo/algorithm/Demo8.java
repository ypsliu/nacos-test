package com.demo.algorithm;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/2/14
 * Time: 13:54
 * 有一个整型数组，数组元素不重复，数组元素先升序后降序，找出最大值
 * Description: 二分法 找数组最大值
 */
public class Demo8 {
    public static void main(String[] args) {

        int[] nums = {1,3,5,7,9,8,6,4,2};
        System.out.println(getLargestNumInArray(nums));
    }

    private static int getLargestNumInArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return nums[left];
    }
}
