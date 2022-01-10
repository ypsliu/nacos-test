package com.demo.pageView;

import cn.hutool.core.collection.CollectionUtil;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/10
 * Time: 11:02
 * Description: 页面点击量统计
 */
public class PVDemo {
    /**
     * Map<Long,Map<Integer,Integer>> = Map<时间块，Map<文章id,访问量>>
     *就是把时间切割为一块块，例如：一篇文章在1小时，30分钟、5分钟的时间内产生了多少阅读量。
     * 时间戳是自 1970 年1月1日（00:00:00 GMT）至当前时间的总数，通过确定时间块大小，
     * 算出当前请求所属的时间戳从 1970 年算起位于第几个时间块，这个算出来的第几个时间块就是小时 key ，即 map 的 key。
     * 举个例子：
     * 我们把时间按照“小时”的维度进行划分。
     * 先把当前的时间转换为为毫秒的时间戳，然后除以一小时，即当前时间 T/1000*60*60=小时key，然后用这个小时序号作为key。
     * 2021-12-26 15:00:00 = 1640502000000毫秒
     * 那么小时key= 1640502000000/1000*60*60=455695，即是距离 1970 年开始算的第 455695 个时间块。
     * 2021-12-26 15:10:00 = 1640502600000毫秒，那么算出来的 key =455695.1，向下取整，key 还是等于 455695。
     * 在 2021-12-25 16:00:00 到 2021-12-25 16:59:59 时间段内产生的阅读量都会映射到 Map 的 key= 455696 的位置去。
     *
     * 其中一级缓存定时器的逻辑是这样的：假设每 5 分钟（可以根据需求调整）从 JVM 的 Map 里面把时间块的阅读 PV 读取出来，然后 push 到 Redis 的 list 数据结构中。
     * list 存储的数据为 Map<文章Id，访问量PV>，即每个时间块的 PV 数据。
     * 另外一个二级缓存定时器的逻辑是这样的：每 6 分钟（需要比一级缓存的时间长），从 Redis 的 list 数据结构中 pop 出数据，即Map<文章Id，访问量PV>。
     * 然后把对应的数据同步到 DB 和 Redis 中。
     *
     */

    //保存时间块和pv数据的map
    public  static final  Map<Long, Map<Integer, Integer>> PV_MAP=new ConcurrentHashMap();
    /**
     * pv请求调用：
     * 即当前时间T/1000*60*60=小时key，然后用这个小时序号作为key。
     * 例如：
     * 2021-11-09 15:30:00 = 1636443000000毫秒
     * 小时key=1636443000000/1000\*60\*60=454567.5=454567
     *
     * 每一次PV操作时，先计算当前时间是那个时间块，然后存储Map中。
     * @param id 文章id
     */
    public void addPV(Integer id) {
        //时间粒度（分钟）
        int timer = 5;
        //时间块
        long timePartition = System.currentTimeMillis()/(1000*60*timer);
        //拿出这个时间块所有的点击数据
        Map<Integer, Integer> timeMap = PV_MAP.get(timePartition);
        if(CollectionUtil.isEmpty(timeMap)){
            timeMap = new ConcurrentHashMap<>();
            timeMap.put(id,new Integer(1));
            //<5分钟的时间块，Map<文章Id,访问量>>
            PV_MAP.put(timePartition,timeMap);
        }else{
            Integer clicks = Optional.ofNullable(timeMap.get(id)).orElse(new Integer(0));
            timeMap.put(id,clicks++);
        }
    }

    /**
     * 一级缓存定时器消费调用方法：
     * 定时器，定时（5分钟）从jvm的map把时间块的阅读pv取出来，
     * 然后push到reids的list数据结构中，list的存储的书为Map<文章id，访问量PV>即每个时间块的pv数据
     */
    public void firstConsumePV(){
        //时间粒度（分钟）
        int timer = 5;
        //时间块
        long timePartition = System.currentTimeMillis()/(1000*60*timer);
        Iterator<Long> iterator = PV_MAP.keySet().iterator();
        while (iterator.hasNext()){
            //取出map的时间块
            Long key = iterator.next();
            //小于当前的时间块，进行消费
            if(key < timePartition){
                //先push
                Map<Integer, Integer> timeMap = PV_MAP.get(key);
                //push到reids的list数据结构中，list的存储的书为Map<文章id，访问量PV>即每个时间块的pv数据
                //this.redisTemplate.opsForList().leftPush(Constants.CACHE_PV_LIST,map);
                //后remove
                PV_MAP.remove(key);
                System.out.println("push success:"+timeMap);
            }
        }
    }

    /**
     * 二级缓存定时器消费
     * 定时(6分钟)，从 Redis 的 list 数据结构 pop 弹出 Map<文章id，访问量PV>，弹出来做了2件事:
     * 先把 Map<文章id，访问量PV>，保存到数据库
     * 再把 Map<文章id，访问量PV>，同步到 Redis 缓存的计数器 incr
     */
    public void secondConsumePV(){

    }
}
