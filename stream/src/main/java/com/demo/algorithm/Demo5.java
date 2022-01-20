package com.demo.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/18
 * Time: 19:29
 * Description:
 *
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * 示例 1：
 * 输入: n = 5, m = 3 输出: 3
 * 示例 2：
 * 输入: n = 10, m = 17 输出: 2
 * 限制：
 * 1 <= n <= 10^5 1 <= m <= 10^6
 */
public class Demo5 {
    public static void main(String[] args) {
        System.out.println(lastRemaining(10,17));
    }

    public static int lastRemaining(int n,int m){
        List<Integer> list = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            list.add(i);
        }
        int removeIndex = 0;
        while (n > 1) {
            removeIndex = (removeIndex + m - 1) % n;
            list.remove(removeIndex);
            n--;
        }
        return list.get(0);
    }
}
