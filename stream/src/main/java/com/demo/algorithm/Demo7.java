package com.demo.algorithm;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/21
 * Time: 14:13
 * Description: try catch finally
 */
public class Demo7 {
    public static int test1(){
        int i = 1;
        try {
            i = 2;
            return i;
        }finally {
            i = 3;
        }
    }
    public static int test2(){
        int i = 1;
        try {
            i = 2;
            return i;
        }finally {
            i = 3;
            return i;
        }
    }

    public static void main(String[] args) {
//        test1() = 2
//        test2() = 3
        System.out.println(test2());
    }
}
