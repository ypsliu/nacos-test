package com.demo.stream;


import cn.hutool.core.bean.BeanUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author lzy
 * @version 1.0
 * @date 2021/11/10 22:13
 */
public class StreamDemo {
//    Stream将要处理的元素集合看作一种流，在流的过程中，借助Stream API对流中的元素进行操作，比如：筛选、排序、聚合等。
//    Stream可以由数组或集合创建，对流的操作分为两种：
//    中间操作，每次返回一个新的流，可以有多个。
//    终端操作，每个流只能进行一次终端操作，终端操作结束后流无法再次使用。终端操作会产生一个新的集合或值。

//    另外，Stream有几个特性：
//    1.stream不存储数据，而是按照特定的规则对数据进行计算，一般会输出结果。
//    2.stream不会改变数据源，通常情况下会产生一个新的集合或一个值。
//    3.stream具有延迟执行特性，只有调用终端操作时，中间操作才会执行。

    private static List<String> LIST_STRING = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
    private static List<Integer> LIST_INT = Arrays.asList(19,22,5,4,11,76,88,44,21,42,66,2,2,55);
    private static List<Person> LIST_PERSON = Arrays.asList(
              new Person("Jack", 7000, 23,"male", "Washington")
            , new Person("Lily", 7000, 25,"female", "Washington")
            , new Person("Anni", 8200, 30,"female", "New York")
            , new Person("Owen", 10500, 35,"male", "New York")
            , new Person("Alisa", 9200, 28, "female", "New York")
    );

    public static void main(String[] args) {

//        create();

//        use_ForeachFindMatch();

//        use_Filter();

//        use_MinMaxCount();

//        use_MapFlatMap();

//        use_reduce();

//        use_ToListToSetToMap();

//        use_CountAveraging();

//        use_PartitioningByGroupBy();

//        use_joining();

//        use_Rreducing();

//        use_sorted();

        use_DistinctSkipLimit();
    }

    /**
     * 1.stream 的创建
     */
    private static void create(){
        //通过 java.util.collection.stream()

        //创建一个顺序流
        Stream<String> collectionStream = LIST_STRING.stream();
        //创建一个并行流
        Stream<String> collectionParallelStream = LIST_STRING.parallelStream();

        //通过 java.util.Arrays.stream(T[] array)
        int[] array = {1,2,3,};
        IntStream arrayStream = Arrays.stream(array);

        //通过 stream() 的静态方法 of(),iterate(),generate()
        Stream<Integer> stream = Stream.of(1,2,3);
        stream.forEach(System.out::println);
        Stream<Integer> stream1 = Stream.iterate(2,(x) -> x+4).limit(5);
        // --> 2 6 10 14 18
        stream1.forEach(System.out::println);
        Stream<Double> stream2 = Stream.generate(Math::random).limit(3);
        stream2.forEach(System.out::println);

        //stream和parallelStream的简单区分：
        //stream是顺序流，由主线程按顺序对流执行操作，而parallelStream是并行流，内部以多线程并行执行的方式对流进行操作，但前提是流中的数据处理没有顺序要求
        //如果流中的数据量足够大，并行流可以加快处速度。
        //除了直接创建并行流，还可以通过parallel()把顺序流转换成并行流

        List<Integer> findList = LIST_INT.stream().filter((x) -> x>6).collect(Collectors.toList());
        List<Integer> findList1 = LIST_INT.parallelStream().filter((x) -> x>6).collect(Collectors.toList());
        List<Integer> findList2 = LIST_INT.stream().parallel().filter((x) -> x>6).collect(Collectors.toList());
        System.out.println(findList);
        System.out.println(findList1);
        System.out.println(findList2);
    }

    /**
     * 2.stream 的使用
     *
     * 在使用stream之前，先理解一个概念：Optional
     * Optional类是一个可以为null的容器对象。
     * 如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
     * 更详细说明请见：https://www.runoob.com/java/java8-optional-class.html
     */

    /**
     * 遍历、匹配 foreach,find,match
     */
    private static void use_ForeachFindMatch(){
        //Stream也是支持类似集合的遍历和匹配元素的，只是Stream中的元素是以Optional类型存在的。

        //遍历输出符合条件的元素
        LIST_INT.stream().filter( x -> x>10).forEach(System.out::println);
        //匹配第一个
        Optional<Integer> findFirst = LIST_INT.stream().filter( x -> x>30).findFirst();
        System.out.println("匹配条件的第一个值："+findFirst.get());
        //任意匹配（适用于并行流）
//      Optional<Integer> findAny = LIST_INT.stream().filter(x -> x>5).findAny();
//      Optional<Integer> findAny = LIST_INT.stream().parallel().filter(x -> x>5).findAny();
        Optional<Integer> findAny = LIST_INT.parallelStream().filter(x -> x>5).findAny();
        System.out.println("匹配条件的任意一个值："+findFirst.get());
        //是否包含符合待定条件的元素
        boolean anyMatch = LIST_INT.stream().anyMatch(x-> x>90);
        System.out.println("是否存在符合条件的值："+anyMatch);

    }

    /**
     * 筛选（filter）
     */
    private static void use_Filter(){
        //筛选，是按照一定的规则校验流中的元素，将符合条件的元素提取到新的流中的操作。

        //案例一：筛选出Integer集合中大于10的元素，并打印出来
        LIST_INT.stream().filter( x-> x>10).forEach(System.out::println);

        //案例二：筛选员工中工资高于9000的人，并形成新的集合。 形成新集合依赖collect（收集）
        List<String> filterList  = LIST_PERSON.stream().filter(x -> x.getSalary() > 8000).map(Person::getName).collect(Collectors.toList());
        System.out.print("高于8000的员工姓名：" + filterList);
    }

    /**
     * 聚合（max/min/count)
     */
    private static void use_MinMaxCount(){
        //获取String集合中最长的元素
        Optional<String> maxString = LIST_STRING.stream().max(Comparator.comparing(String::length));
        System.out.println("最长的字符串："+maxString.get());

        //获取Integer集合中的最大值
        //自然排序
        Optional<Integer> maxInt = LIST_INT.stream().max(Integer::compareTo);
        //自定义排序
        Optional<Integer> maxInt2 = LIST_INT.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("自然排序的最大值："+maxInt.get());
        System.out.println("自定义排序的最大值："+maxInt2.get());

        //获取员工工资最高的人
        Optional<Person> maxPerson = LIST_PERSON.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("获取工资最高的人："+maxPerson.get().getName());

        //计算Integer集合中大于6的元素的个数
        Long count = LIST_INT.stream().filter(x->x>6).count();
        System.out.println("大于6的元素个数："+count);
     }

    /**
     * 映射(map/flatMap)
     */
    private static void use_MapFlatMap(){
        //映射，可以将一个流的元素按照一定的映射规则映射到另一个流中。分为map和flatMap
        //map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        //flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。

        //英文字符串数组的元素全部改为大写。整数数组每个元素+3。
        List<String> strList = LIST_STRING.stream().map( X -> X.toUpperCase()).collect(Collectors.toList());
        System.out.println("元素全部改为大写："+strList);
        List<Integer> intList = LIST_INT.stream().map(x -> x+3).collect(Collectors.toList());
        System.out.println("整租数组元素+3："+intList);

        //将全部员工的薪资加1000  原对象会改变
        List<Person> personList = LIST_PERSON.stream().map(person -> {
            person.setSalary(person.getSalary()+1000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("加薪1000后的员工："+personList);
        System.out.println("加薪前："+LIST_PERSON);

        //不改变原对象
        List<Person> personList1 = LIST_PERSON.stream().map(person -> {
            Person copyPerson = new Person();
            BeanUtil.copyProperties(person,copyPerson);
            copyPerson.setSalary(copyPerson.getSalary()+10000);
            return copyPerson;
        }).collect(Collectors.toList());
        System.out.println("加薪10000后的员工："+personList1);
        System.out.println("加薪前："+LIST_PERSON);

        //将两个字符串合成一个字符串
        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> list1 = list.stream().flatMap(s -> {
            String[] strings = s.split(",");
            Stream<String> s1 = Arrays.stream(strings);
            return s1;
        }).collect(Collectors.toList());
        System.out.println("处理前的集合：" + list);
        System.out.println("处理后的集合：" + list1);
     }

    /**
     * 归纳
     */
    private static void use_reduce(){
        //归约，也称缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。

        //求Integer集合的元素之和、乘积和最大值。
        //求和方式1
        Optional<Integer> sum = LIST_INT.stream().reduce(Integer::sum);
        //求和方式2
        Optional<Integer> sum1 = LIST_INT.stream().reduce((x,y) -> x+y);
        //求和方式3
        Integer sum2 = LIST_INT.stream().reduce(0,Integer::sum);
        System.out.println("list求和：" + sum.get() + "," + sum1.get() + "," + sum2);

        //求乘积
        Optional<Integer> product = LIST_INT.stream().reduce((x,y) -> x*y);
        System.out.println("list求积：" + product.get());

        //求最大值
        Optional<Integer> max1 = LIST_INT.stream().reduce((X,Y) -> X>Y?X:Y);
        Integer max = LIST_INT.stream().reduce(1,Integer::max);
        System.out.println("list求最大值：" + max1.get() + "," + max);

        //案例二：求所有员工的工资之和和最高工资。
       Optional<Integer> sum3 =  LIST_PERSON.stream().map(Person::getSalary).reduce(Integer::sum);
       Integer sum4 = LIST_PERSON.stream().reduce(0,(s,p) -> s+=p.getSalary(),Integer::sum);
        Integer sum5 = LIST_PERSON.stream().reduce(0,(s,p) -> s+=p.getSalary(),(x,y) -> x+y);

        Optional<Integer> max2 = LIST_PERSON.stream().map(Person::getSalary).reduce(Integer::max);
        Integer max3 = LIST_PERSON.stream().reduce(0,(m,p) -> m>p.getSalary()?m:p.getSalary(),Integer::max);
    }

    /**
     * 归集(toList/toSet/toMap)
     */
    private static void use_ToListToSetToMap(){
        //因为流不存储数据，那么在流中的数据完成处理后，需要将流中的数据重新归集到新的集合里。toList、toSet和toMap比较常用，另外还有toCollection、toConcurrentMap等复杂一些的用法。

        List<Integer> list = LIST_INT.stream().filter(x -> x%2 ==0).collect(Collectors.toList());
        Set<Integer> set = LIST_INT.stream().filter(x -> x%2 ==0).collect(Collectors.toSet());
        Map<String,Person> map = LIST_PERSON.stream().filter(x -> x.getSalary() > 8000)
                .collect(Collectors.toMap(
                        Person::getName, y -> y
        ));
        System.out.println("toList:" + list);
        System.out.println("toSet:" + set);
        System.out.println("toMap:" + map);
    }

    /**
     * 统计
     */
    private static void use_CountAveraging(){
//        Collectors提供了一系列用于数据统计的静态方法：
//        计数：count
//        平均值：averagingInt、averagingLong、averagingDouble
//        最值：maxBy、minBy
//        求和：summingInt、summingLong、summingDouble
//        统计以上所有：summarizingInt、summarizingLong、summarizingDouble

        //总数
        Long count = LIST_PERSON.stream().count();
        Long count1 = LIST_PERSON.stream().collect(Collectors.counting());
        System.out.println("员工总数：" + count + "|"+count1);

        //平均工资
        Double average = LIST_PERSON.stream().collect(Collectors.averagingDouble(Person::getSalary));
        System.out.println("员工平均工资：" + average);

        //求最高工资
        Optional<Integer> max = LIST_PERSON.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));
        System.out.println("员工最高工资：" + max);

        //求工资之和
        Integer sum =  LIST_PERSON.stream().collect(Collectors.summingInt(Person::getSalary));
        System.out.println("员工工资之和：" + sum);

        //一次统计所有
        DoubleSummaryStatistics collect =  LIST_PERSON.stream().collect(Collectors.summarizingDouble(Person::getSalary));
        System.out.println("员工工资所有统计：" + collect);
    }

    /**
     * 分组(partitioningBy/groupingBy)
     */
    private static void use_PartitioningByGroupBy(){
//        分区：将stream按条件分为两个Map，比如员工按薪资是否高于8000分为两部分。
//        分组：将集合分为多个Map，比如员工按性别分组。有单级分组和多级分组。
        Map<Boolean,List<Person>> map = LIST_PERSON.stream().collect(Collectors.partitioningBy(x -> x.getSalary()>8000));
        System.out.println(map);
        Map<String,List<Person>> map1 = LIST_PERSON.stream().collect(Collectors.groupingBy(Person::getSex));
        System.out.println(map1);
        Map<String,Map<String,List<Person>>> map2 = LIST_PERSON.stream().collect(Collectors.groupingBy(
                Person::getSex,Collectors.groupingBy(Person::getArea)
        ));
        System.out.println(map2);
    }

    /**
     * 接合(joining)
     */
    private static void use_joining(){
        //joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串。
        String str = LIST_PERSON.stream().map(Person::getName).collect(Collectors.joining("-"));
        System.out.println(str);

    }


    /**
     *    归约(reducing)
      */
    private static void use_Rreducing(){
        //Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持。
        // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
        Integer sum = LIST_PERSON.stream().collect(Collectors.reducing(0, Person::getSalary,(x, y)->x+y-5000));
        System.out.println(sum);
        Integer sum1 = LIST_PERSON.stream().map(Person::getSalary).reduce(Integer::sum).get();
        System.out.println(sum1);
    }

    /**
     * 排序(sorted)
     */
    private static void use_sorted() {
//        sorted，中间操作。有两种排序：
//        sorted()：自然排序，流中元素需实现Comparable接口
//        sorted(Comparator com)：Comparator排序器自定义排序

        // 按工资升序排序（自然排序）
        List<String> list = LIST_PERSON.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName).collect(Collectors.toList());
        System.out.println("按工资升序排序：" + list);
        // 按工资倒序排序
        List<String> list1 = LIST_PERSON.stream().sorted(Comparator.comparing(Person::getSalary)
                .reversed()).map(Person::getName).collect(Collectors.toList());
        System.out.println("按工资倒序排序：" + list1);
        // 先按工资再按年龄升序排序
        List<String> list2 = LIST_PERSON.stream().sorted(Comparator.comparing(Person::getSalary))
                .sorted(Comparator.comparing(Person::getAge))
                .map(Person::getName).collect(Collectors.toList()); //错误写法 结果是按年龄的优先级在先
        List<String> list3 = LIST_PERSON.stream().sorted(Comparator.comparing(Person::getAge))
                .sorted(Comparator.comparing(Person::getSalary))
                .map(Person::getName).collect(Collectors.toList());
        List<String> list4 = LIST_PERSON.stream().sorted(Comparator.comparing(Person::getSalary)
                        .thenComparing(Person::getAge)).map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("先按工资再按年龄升序排序:" + list2);
        System.out.println("先按工资再按年龄升序排序:" + list3);
        System.out.println("先按工资再按年龄升序排序:" + list4);

        // 先按工资再按年龄自定义排序（降序）
        List<String> list5 = LIST_PERSON.stream().sorted(Comparator.comparing(Person::getSalary)
                        .thenComparing(Person::getAge).reversed()).map(Person::getName)
                .collect(Collectors.toList()); //错误写法 这样都会变成降序
        System.out.println("先按工资再按年龄降序排序:" + list5);
        List<String> list6 = LIST_PERSON.stream().sorted(Comparator.comparing(Person::getAge).reversed())
                .sorted(Comparator.comparing(Person::getSalary))
                .map(Person::getName).collect(Collectors.toList());
        System.out.println("先按工资再按年龄降序排序:" + list6);
        List<String> list7 = LIST_PERSON.stream().sorted(
                (x, y) -> {
                    if (x.getSalary() == y.getSalary()) {
                        return y.getAge() - x.getAge();
                    }
                    return x.getSalary() - y.getSalary();
                }
        ).map(Person::getName).collect(Collectors.toList());
        System.out.println("先按工资再按年龄降序排序:" + list7);
    }

    /**
     * 提取/组合
     */
    private static void use_DistinctSkipLimit(){
    //流也可以进行合并、去重、限制、跳过等操作。
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "d", "e", "f", "g" };
        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> list = Stream.concat(stream1,stream2).collect(Collectors.toList());
        System.out.println("合并后去重："+list);
        List<String> list1 = list.stream().distinct().collect(Collectors.toList());
        System.out.println("合并后去重："+list1);
        // limit：限制从流中获得前n个数据
        List<Integer> list2 = Stream.iterate(1,x->x+2).limit(10).collect(Collectors.toList());
        System.out.println("limit：" + list2);
        // skip：跳过前n个数据
        List<Integer> list3 =  Stream.iterate(1,x->x+3).skip(1).limit(5).collect(Collectors.toList());
        System.out.println("skip：" + list3);
    }
}
