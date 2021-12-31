package com.demo.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author lz
 * @date 2021-11-16 17:49
 */
public class Restaurant {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH) );
        //筛选出热量大于300的前三种食物 limits 限制位置，并不进行排序
        List<String> threeHighCaloriesDishNames =
                menu.stream()
                    .filter(dish -> dish.getCalories()>300)
                    .map(Dish::getName)
                    .limit(3)
                    .collect(Collectors.toList());
        System.out.println(threeHighCaloriesDishNames);

        //外部迭代与内部迭代
        List<String> names = new ArrayList<>();
        for (Dish dish:menu){
            names.add(dish.getName());
        }
        System.out.println(names);
        names.clear();
        Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()){
            Dish d = iterator.next();
            names.add(d.getName());
        }
        System.out.println(names);
        names.clear();
        names = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(names);
        //中间操作
        /**
         * 诸如filter或sorted等中间操作会返回另一个流。这让多个操作可以连接起来形成一个查
         * 询。重要的是，除非流水线上触发一个终端操作，否则中间操作不会执行任何处理——它们很懒。
         * 这是因为中间操作一般都可以合并起来，在终端操作时一次性全部处理
         */
        names.clear();
        names = menu.stream()
                .filter( dish -> {
                    System.out.println("filtering"+ dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("mapping"+dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(names);
        //终端操作
        /**
         * 终端操作会从流的流水线生成结果。其结果是任何不是流的值，比如List、Integer，甚
         * 至void。例如，在下面的流水线中，forEach是一个返回void的终端操作，它会对源中的每道
         * 菜应用一个Lambda
         */
        long count = menu.stream()
                         .filter(dish -> dish.getCalories()>300)
                         .distinct()
                         .limit(3)
                         .count();
        System.out.println(count);
        //流水线中最后一个操作count返回一个long，这是一个非Stream的值。因此它是
        //一个终端操作。所有前面的操作，filter、distinct、limit，都是连接起来的，并返回一
        //个Stream，因此它们是中间操作。

        //使用流
        /**中间操作
         * 操 作        类 型         返回类型        操作参数            函数描述符
         * filter       中间          Stream<T>       Predicate<T>        T -> boolean
         * map          中间          Stream<R>       Function<T, R>      T -> R
         * limit        中间          Stream<T>
         * sorted       中间          Stream<T>       Comparator<T>       (T, T) -> int
         * distinct     中间          Stream<T>
         */
        /**终端操作
         * 操 作        类 型                               目 的
         * forEach      终端          消费流中的每个元素并对其应用 Lambda。这一操作返回 void
         * count        终端          返回流中元素的个数。这一操作返回 long
         * collect      终端          把流归约成一个集合，比如 List、Map 甚至是 Integer
         */

        /**
         * 筛选和切片
         */

        /**
         * 1.用谓词筛选
         */
        //Streams接口支持filter方法（你现在应该很熟悉了）。该操作会接受一个谓词（一个返回
        //boolean的函数）作为参数，并返回一个包括所有符合谓词的元素的流。
        //筛选出所有素菜，创建一张素食菜单
        List<String> vegetarianMenu = menu.stream()
                .filter(dish -> dish.isVegatarian())
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("vegetarianMenu："+vegetarianMenu);

        /**
         * 2.筛选各异的元素（去重）
         */
        //流还支持一个叫作distinct的方法，它会返回一个元素各异（根据流所生成元素的
        //hashCode和equals方法实现）的流
        List<Integer> numbers = Arrays.asList(1,2,3,2,2,2,4);
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n%2 == 0)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("evenNumbers："+evenNumbers);


        /**
         * 3.截短流
         */
        //流支持limit(n)方法，该方法会返回一个不超过给定长度的流。所需的长度作为参数传递
        //给limit。如果流是有序的，则最多会返回前n个元素。
        //选出热量超过300卡路里的头三道菜
        List<String> threeHighDish = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("threeHighDish："+threeHighDish);
        //请注意limit也可以用在无序流上，比如源是一个Set。这种情况下，limit的结果不会以
        //任何顺序排列。

        /**
         * 4.跳过元素
         */
        //流还支持skip(n)方法，返回一个扔掉了前n个元素的流。如果流中元素不足n个，则返回一
        //个空流。请注意，limit(n)和skip(n)是互补的！
        //跳过超过300卡路里的头两道菜，并返回剩下的
        List<String> skipTwoOthersDish = menu.stream()
                .filter(dish -> dish.getCalories() >300)
                .skip(2)
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("skipTwoOthersDish："+skipTwoOthersDish);


        /**
         * 映射
         */
        /**
         * 1.对流中每一个元素应用函数
         */
        //流支持map方法，它会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映
        //射成一个新的元素（使用映射一词，是因为它和转换类似，但其中的细微差别在于它是“创建一
        //个新版本”而不是去“修改”
        List<String> allDishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("allDishNames："+allDishNames);
        List<String> words = Arrays.asList("Java 8","Lambdas","In","Actions");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("wordLengths："+wordLengths);
        //要找出每道菜的名称有多长
        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("dishNameLengths："+dishNameLengths);

        /**
         * 2.流的扁平化
         */
         //flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流
        //对于一张单词表，如何返回一张列表，列出里面 各不相同的字符 呢？例如，给定单词列表
        //["Hello","World"]，你想要返回列表["H","e","l", "o","W","r","d"]。
        List<String> words1 = Arrays.asList("Hello","World");
        List<String> flagWords = words1.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        System.out.println("flagWords："+flagWords);
        //给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？例如，给定[1, 2, 3, 4,
        //5]，应该返回[1, 4, 9, 16, 25]。
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer>  squares = numbers1.stream()
                .map(n -> n*n)
                .collect(Collectors.toList());
        System.out.println("squares: "+squares);
        //给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应
        //该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代
        //表数对。
        List<Integer> numbers2 = Arrays.asList(1,2,3);
        List<Integer> numbers3 = Arrays.asList(3,4);
        List<List<Integer>> pairs = numbers2.stream()
                .flatMap(i -> numbers3.stream()
                        .map(j -> Arrays.asList(i,j)))
                .collect(Collectors.toList());
        System.out.println("pairs: "+pairs);
        //如何扩展前一个例子，只返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的。
        List<List<Integer>> pairs1 = numbers2.stream()
                .flatMap(i -> numbers3.stream()
                        .filter(j -> (i+j)%3 == 0)
                        .map(j -> Arrays.asList(i,j)))
                .collect(Collectors.toList());
        System.out.println("pairs1: "+pairs1);


        /**
         * 匹配和查找
         * 另一个常见的数据处理套路是看看数据集中的某些元素是否匹配一个给定的属性。Stream
         * API通过allMatch、anyMatch、noneMatch、findFirst和findAny方法提供了这样的工具
         */
        /**
         * 1.检查谓词是否至少匹配一个元素
         */
        //anyMatch方法可以回答“流中是否有一个元素能匹配给定的谓词”
        //菜单里面是否有素食可选择
        Boolean hasVegatarian = menu.stream()
                .anyMatch(Dish::isVegatarian);
        System.out.println("hasVegatarian: "+hasVegatarian);

        /**
         * 2.检查谓词是否匹配所有元素
         */
        //allMatch方法的工作原理和anyMatch类似，但它会看看流中的元素是否都能匹配给定的谓词
        //用它来看看菜品是否有利健康（即所有菜的热量都低于1000卡路里）
        Boolean isHealthy = menu.stream()
                .allMatch(dish -> dish.getCalories() < 1000);
        System.out.println("isHealthy: "+isHealthy);
        //和allMatch相对的是noneMatch。它可以确保流中没有任何元素与给定的谓词匹配
        Boolean isHealthy1 = menu.stream()
                .noneMatch(dish -> dish.getCalories() >=1000);
        System.out.println("isHealthy1: "+isHealthy1);

        /**
         * 3.查找元素
         */
        //findAny方法将返回当前流中的任意元素
        //你可能想找到一道素食菜肴
       menu.stream()
               .filter(Dish::isVegatarian)
               .findAny()
               .ifPresent(d -> System.out.println("anyOneVegetarian: "+d.getName()));

        /**
         * 4.查找第一个元素
         */
        //有些流有一个出现顺序（encounter order）来指定流中项目出现的逻辑顺序（比如由List或
        //排序好的数据列生成的流）。对于这种流，你可能想要找到第一个元素。为此有一个findFirst
        //方法，它的工作方式类似于findany。
        //给定一个数字列表，下面的代码能找出第一个平方能被3整除的数
        List<Integer> numbers4 = Arrays.asList(1,2,3,4,5);
        numbers4.stream()
                .map(x -> x*x)
                .filter(x -> x%3 ==0)
                .findFirst()
                .ifPresent( x -> System.out.println("firstNum: "+x));


        /**
         * 归纳
         */

        /**
         * 1.求和
         */
        //计算菜单中的总卡路里
        Integer totalCalories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, (a,b) -> a+b);
        System.out.println("totalCalories: "+totalCalories);

        /**
         * 最大值与最小值
         */
        //Lambda (x, y) -> x < y ? x : y而不是Integer::min or Integer::max
         menu.stream()
                .map(Dish::getCalories)
                .reduce(Integer::max)
                .ifPresent(d -> System.out.println("maxCalories: "+d));

         menu.stream()
                 .map(Dish::getCalories)
                 .reduce((x,y) -> x<y?x:y)
                 .ifPresent(d -> System.out.println("minCalories: "+d));
         //怎样用map和reduce方法数一数流中有多少个菜呢
        Integer mapReduceCount = menu.stream()
                .map(d -> 1)
                .reduce(0,(x,y)->x+y);
        System.out.println("mapReduceCount: "+mapReduceCount);
        Long normalCount = menu.stream().count();
        System.out.println("normalCount: "+normalCount);

        /**
         * 中间操作和终端操作
         * 操 作            类 型                   返回类型            使用的类型/函数式接口             函数描述符
         * filter           中间                   Stream<T>          Predicate<T>                     T -> boolean
         * distinct         中间(有状态-无界)      Stream<T>
         * skip             中间(有状态-有界)      Stream<T>           long
         * limit            中间(有状态-有界)      Stream<T>           long
         * map              中间                  Stream<R>               Function<T, R>              T -> R
         * flatMap          中间                  Stream<R>               Function<T, Stream<R>>      T -> Stream<R>
         * sorted           中间(有状态-无界)      Stream<T>           Comparator<T>                   (T, T) -> int
         * anyMatch         终端                  boolean             Predicate<T>                    T -> boolean
         * noneMatch        终端                  boolean             Predicate<T>                    T -> boolean
         * allMatch         终端                  boolean             Predicate<T>                    T -> boolean
         * findAny          终端                  Optional<T>
         * findFirst        终端                  Optional<T>
         * forEach          终端                  void                Consumer<T>                     T -> void
         * collect          终端                  R                   Collector<T, A, R>
         * reduce           终端(有状态-有界)      Optional<T>         BinaryOperator<T>               (T, T) -> T
         * count            终端                  long
         */

        /**
         * 数值流
         */
        /**
         * 1.原始类型流特化
         */
//        Java 8引入了三个原始类型特化流接口来解决这个问题：IntStream、DoubleStream和
//        LongStream，分别将流中的元素特化为int、long和double，从而避免了暗含的装箱成本。每
//        个接口都带来了进行常用数值归约的新方法，比如对数值流求和的sum，找到最大元素的max。
//        此外还有在必要时再把它们转换回对象流的方法。要记住的是，这些特化的原因并不在于流的复
//        杂性，而是装箱造成的复杂性——即类似int和Integer之间的效率差异
        //映射到数值流
        //将流转换为特化版本的常用方法是mapToInt、mapToDouble和mapToLong。这些方法和前
        //面说的map方法的工作方式一样，只是它们返回的是一个特化流，而不是Stream<T>
        menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        //转换会对象流
        IntStream intStream = menu.stream()
                .mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();
        // 默认值OptionalInt
        OptionalInt optionalInt = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        optionalInt.ifPresent(d -> System.out.println("optionalInt: "+d));
        optionalInt.orElse(1);//如果没有最大值的话，你就可以显式处理OptionalInt去定义一个默认值了

        /**
         * 2.数值范围
         */
        //Java 8引入了两个可以用于IntStream和LongStream的静态方法，帮助生成这种范围range和rangeClosed
        //生成0-100得所有偶数
        IntStream.rangeClosed(0,100)
                .filter(x -> x%2 ==0)
                .forEach(System.out::print); //[a,b] 含头含尾
        System.out.println();
        IntStream.range(0,100)
                .filter(x -> x%2 ==0)
                .forEach(System.out::print);//[a,b) 含头不含尾

        /**
         * 3.数值流应用：勾股数
         */
        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .mapToObj(
                                                b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                                        .filter(t -> t[2] % 1 == 0));


        /**
         * 收集器简介
         */
        /**
         * 1.收集器用作高级归纳
         */
        //刚刚的结论又引出了优秀的函数式API设计的另一个好处：更易复合和重用。收集器非常有
        //用，因为用它可以简洁而灵活地定义collect用来生成结果集合的标准。更具体地说，对流调用
        //collect方法将对流中的元素触发一个归约操作（由Collector来参数化）
        menu.stream()
                .collect(Collectors.toList());

        /**
         * 2.预定义收集器
         */
//        从Collectors类提供的工厂方法（例如groupingBy）创建的收集器。它们主要提供了三大功能：
        //将流元素归约和汇总为一个值
        //元素分组
        //元素分区

        /**
         * 归纳和汇总
         */
        /**
         * 1.查找流中的最大值和最小值
         */
        //查找总数
        menu.stream()
                .collect(Collectors.counting());
        //最大值
        menu.stream()
                .collect(Collectors.maxBy(
                        Comparator.comparingInt(Dish::getCalories)
                ))
        .ifPresent(d -> System.out.println(d));
        //最小值
        menu.stream()
                .collect(Collectors.minBy(
                        Comparator.comparing(Dish::getCalories)
                ))
        .ifPresent(d -> System.out.println(d));

        /**
         * 2.汇总
         */
        //求和
        menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        //求平均值
        menu.stream()
                .collect(Collectors.averagingInt(Dish::getCalories));
        //汇总
        IntSummaryStatistics intSummaryStatistics = menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println("intSummaryStatistics: "+intSummaryStatistics);

        /**
         * 3.连接字符串
         */
        String menuString = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(","));

        /**
         * 4.广义的归纳汇总
         */
        //事实上，我们已经讨论的所有收集器，都是一个可以用reducing工厂方法定义的归约过程
        //的特殊情况而已。Collectors.reducing工厂方法是所有这些特殊情况的一般化
//        它需要三个参数。
//           第一个参数是归约操作的起始值，也是流中没有元素时的返回值，所以很显然对于数值和而言0是一个合适的值。
//           第二个参数就是你在6.2.2节中使用的函数，将菜肴转换成一个表示其所含热量的int。
//           第三个参数是一个BinaryOperator，将两个项目累积成一个同类型的值。这里它就是对两个int求和。
        int totalCalories1 = menu.stream()
                .collect(Collectors.reducing(
                        0,Dish::getCalories,(x,y) -> x+y
                ));
        Optional<Dish> mostCalorieDish = menu.stream()
                .collect(Collectors.reducing(
                        (x,y) -> x.getCalories()>y.getCalories()? x:y
                ));
//        reduce方法
//        旨在把两个值结合起来生成一个新值，它是一个不可变的归约。与此相反，collect方法的设
//        计就是要改变容器，从而累积要输出的结果。这意味着，上面的代码片段是在滥用reduce方
//        法，因为它在原地改变了作为累加器的List。你在下一章中会更详细地看到，以错误的语义
//        使用reduce方法还会造成一个实际问题：这个归约过程不能并行工作，因为由多个线程并发
//        修改同一个数据结构可能会破坏List本身。在这种情况下，如果你想要线程安全，就需要每
//        次分配一个新的List，而对象分配又会影响性能。这就是collect方法特别适合表达可变容
//        器上的归约的原因，更关键的是它适合并行操作

        /**
         * 分组
         */
        /**
         * 多级分组
         */
        Map<Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
                .collect(Collectors.groupingBy(
                    Dish::getType,Collectors.groupingBy(
                            d->{
                                if(d.getCalories() < 400){
                                    return  CaloricLevel.DIET;
                                }else if(d.getCalories() <700){
                                    return  CaloricLevel.NORMA;
                                }else{
                                    return  CaloricLevel.FAT;
                                }
                            }
                        )
                ));
        System.out.println("dishesByTypeCaloricLevel: "+dishesByTypeCaloricLevel);

        /**
         * 按子集分组
         */
        Map<Type, Long> typesCount = menu.stream()
                .collect(Collectors.groupingBy(
                        Dish::getType,Collectors.counting()
                ));
        System.out.println("typesCount: "+typesCount);
        Map<Type, Optional<Dish>> mostCaloricByType = menu.stream()
                .collect(Collectors.groupingBy(
                        Dish::getType,Collectors.maxBy(
                                Comparator.comparingInt(Dish::getCalories))
                ));
        System.out.println("mostCaloricByType: "+mostCaloricByType);
        // 把收集器的结果转换为另一种类型
        Map<Type, Dish> mostCaloricByType1 = menu.stream()
                .collect(Collectors.groupingBy(
                        Dish::getType,Collectors.collectingAndThen(
                                Collectors.maxBy(
                                        Comparator.comparingInt(Dish::getCalories)),Optional::get
                        )
                ));
        System.out.println("mostCaloricByType1: "+mostCaloricByType1);

        /**
         * 分区
         */
        Map<Boolean,List<Dish>> partitionedMenu = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegatarian));
        Map<Boolean, Map<Type, List<Dish>>> vegetarianDishesByType =
                menu.stream().collect(
                        Collectors.partitioningBy(Dish::isVegatarian,
                                Collectors.groupingBy(Dish::getType)));

        /**
         * Collectors类的静态工厂方法
         * 工厂方法                     返回类型                              用 于
         * toList                       List<T>                       把流中所有项目收集到一个 List
         * 使用示例：List<Dish> dishes = menuStream.collect(toList());
         *
         * toSet                       Set<T>                       把流中所有项目收集到一个 Set，删除重复项
         * 使用示例：Set<Dish> dishes = menuStream.collect(toSet());
         *
         * toCollection                 Collection<T>               把流中所有项目收集到给定的供应源创建的集合
         * 使用示例：Collection<Dish> dishes = menuStream.collect(toCollection(),ArrayList::new);
         *
         * counting                     Long                        计算流中元素的个数
         * 使用示例：long howManyDishes = menuStream.collect(counting());
         *
         * summingInt                   Integer                     对流中项目的一个整数属性求和
         * 使用示例：int totalCalories = menuStream.collect(summingInt(Dish::getCalories));
         *
         * averagingInt                 Double                      计算流中项目 Integer 属性的平均值
         * 使用示例：double avgCalories = menuStream.collect(averagingInt(Dish::getCalories));
         *
         * summarizingInt               IntSummaryStatistics   收集关于流中项目 Integer 属性的统计值，例如最大、最小、总和与平均值
         * 使用示例：IntSummaryStatistics menuStatistics = menuStream.collect(summarizingInt(Dish::getCalories));
         *
         * joining`                     String                  连接对流中每个项目调用 toString 方法所生成的字符串
         * 使用示例：String shortMenu = menuStream.map(Dish::getName).collect(joining(", "));
         *
         * minBy/maxBy                  Optional<T>             一个包裹了流中按照给定比较器选出的最小元素的 Optional，或如果流为空则为 Optional.empty()
         * 使用示例：Optional<Dish> lightest = menuStream.collect(minBy(comparingInt(Dish::getCalories)));
         *
         * reducing                     归约操作产生的类型       从一个作为累加器的初始值开始，利用 BinaryOperator 与流中的元素逐个结合，从而将流归约为单个值
         * 使用示例：int totalCalories = menuStream.collect(reducing(0, Dish::getCalories, Integer::sum));
         *
         * collectingAndThen            转换函数返回的类型        包裹另一个收集器，对其结果应用转换函数
         * 使用示例：int howManyDishes = menuStream.collect(collectingAndThen(toList(), List::size));
         *
         * groupingBy                   Map<K, List<T>>         根据项目的一个属性的值对流中的项目作问组，并将属性值作为结果 Map 的键
         * 使用示例：Map<Dish.Type,List<Dish>> dishesByType = menuStream.collect(groupingBy(Dish::getType));
         *
         * partitioningBy               Map<Boolean,List<T>>     根据对流中每个项目应用谓词的结果来对项目进行分区
         * 使用示例：Map<Boolean,List<Dish>> vegetarianDishes = menuStream.collect(partitioningBy(Dish::isVegetarian));
         */
    }
}
