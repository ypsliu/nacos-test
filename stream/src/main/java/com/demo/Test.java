package com.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 16:24
 * Description: No Description
 */
public class Test {
    public static void main(String[] args) {
        short s1 = 1;
        s1 = (short) (s1 +1);
        s1 += 1; //有隐含的强制类型转换。

        String str = new String();

        List<String> list = new ArrayList<>();
        list. add("x");
        //只读集合
        Collection<String> clist = Collections. unmodifiableCollection(list);
        clist.add("s");//报错

        //两种插入是有区别的 非顺序插入和删除 是会copy的
        list.add("s");
        list.add(1,"s");
        list.remove(1);
    }
}
