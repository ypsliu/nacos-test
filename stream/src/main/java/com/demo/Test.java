package com.demo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.demo.stream.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openjdk.jol.vm.VM;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
//        clist.add("s");//报错

        //两种插入是有区别的 非顺序插入和删除 是会copy的
        list.add("s");
        list.add(1,"s");
        list.remove(1);

        HashMap<String, String> map = new HashMap<>();


        List<String> list1 = new ArrayList<>();
        list1.add("one");
        list1.add("two");
        list1.add("three");
        System.out.println(list1.size());
        for (String s:list1){
            System.out.println(list1.size());
            /**
             * 如果移除的是倒数第二个 没有CME 异常
             */
            if(s.equals("two")){
                list1.remove(s);
            }
        }
//        for (String s:list1){
//            list1.add("three");
//        }
        System.out.println(list1);

        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        Iterator<String> iterator = list2.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
            if(next.equals("3")){
                iterator.remove();
            }
        }
        System.out.println(list2);

        List<Integer> list3 = IntStream.rangeClosed(0,10)
                .parallel()
                .boxed()
                .collect(Collectors.toList());
        System.out.println(list3);
    }

    @org.junit.Test
    public void copyTest(){
        User u1 = new User(1,"lzy",23);
        User u2 = new User(2,"LZY",25);

        System.out.println(VM.current().addressOf(u2));
        BeanUtil.copyProperties(u1,u2,"id");
        System.out.println(VM.current().addressOf(u2));
        System.out.println(u2);
        System.out.println(test());
    }

    @org.junit.Test
    public void UTCTest(){
        String str = "2022-06-29T07:08:53.506Z[UTC]";
        DateTime dateTime = DateUtil.parseUTC(str);
        System.out.println(dateTime);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class User{
        private int id;
        private String name;
        private int age;
    }

    private boolean test(){
        Boolean result ;
        return result = true;
    }

}
