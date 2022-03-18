package com.demo.limit;

import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/17
 * Time: 13:47
 * Description: 滑动窗口
 */
@Data
public class SlidingTimeWindow {
    /**
     * 单位时间分割多少块
     */
    private int slot;
    /**
     * 单位时间的次数
     */
    private long limit;
    /**
     * 窗口时间总长
     */
    private Long windowTime;
    /**
     * 单位时间颗粒
     */
    private TimeUnit timeUnit;
    /**
     * 每个slot的时间段
     */
    private long slotTime;
    /**
     * 记录窗口滑动到的Node[00001]
     */
    private Node lastNode;

    public SlidingTimeWindow(int slot, long limit, Long windowTime, TimeUnit timeUnit) {
        this.slot = slot;
        this.limit = limit;
        this.windowTime = windowTime;
        this.timeUnit = timeUnit;
        this.slotTime = timeUnit.toMillis(windowTime)/slot;
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        lastNode = null;//初始化 窗口
        long current = System.currentTimeMillis();
        Node currentNode = null;
        for(int i=0;i < slot; i++){
            if(lastNode == null){
                lastNode = new Node(current,new LongAdder());
                currentNode = lastNode;
            }else{
                Node next = new Node(lastNode.getTime()-slotTime,new LongAdder());
                lastNode.setNext(next);
                lastNode = lastNode.getNext();
            }
        }
        lastNode = currentNode;
    }

    /**
     * 检查是否超限
     */
    public synchronized boolean checkAndAdd(){
        reset();
        long num = getSum();
        if(num >= limit){
            //触发限流
            return false;
        }
        lastNode.addCount();
        System.out.println(lastNode);
        return true;
    }

    /**
     * 滑动窗口
     * @param num
     * @param currentTimeMillis
     */
   private void reset(int num,long currentTimeMillis){
        if(num <= 0){
            return;
        }else if(num >= slot){
            init();
            return;
        }else{
            //窗口滑动偏移量 num 即移除链表尾部num,并增加头部num
           for(int i=0;i<num;i++){
               NodeAction.removeEndNode(lastNode);
               Node node = new Node(currentTimeMillis,new LongAdder());
               node.setNext(lastNode);
               lastNode = node;
           }

        }

   }

   private void reset(){
       long currentTimeMillis = System.currentTimeMillis();
       long time = lastNode.getTime();
       int count = (int)((currentTimeMillis-time)/slotTime);
       reset(count,currentTimeMillis);
   }

    /**
     * 获取当前窗口总数
     * @return
     */
   private long getSum(){
       long sum = 0L;
       Node currentNode = lastNode;
       for(int i=0;i<slot;i++){
           sum += currentNode.getCount().longValue();
           currentNode = currentNode.getNext();
       }
       return sum;
   }

    public static void main(String[] args) throws InterruptedException {
        SlidingTimeWindow slidingTimeWindow = new SlidingTimeWindow(5,2,5L,TimeUnit.SECONDS);
        System.out.println(slidingTimeWindow);
        for (int i=0;i<10;i++){
            TimeUnit.SECONDS.sleep(1L);
            System.out.println(slidingTimeWindow.checkAndAdd());
            System.out.println(slidingTimeWindow);
        }





    }

}
