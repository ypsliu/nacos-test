package com.demo.limit;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/17
 * Time: 19:35
 * Description: No Description
 */
public class Test {
    public static void main(String[] args) {
        for(int i=0;i<20;i++){
            new Thread(
                    ()->{
                        System.out.println(LimitManager.getInstance().limiting("www.baidu.com"));
                    }
            ).start();
        }
    }
}
