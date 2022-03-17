package com.demo.limit;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.LongAdder;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/17
 * Time: 13:44
 * Description: No Description
 */
public class Node {
    private long id;
    private long time;
    private LongAdder count = new LongAdder();
    private Node next;

    public Node(long time, LongAdder count) {
        this.time = time;
        this.count = count;
        this.id = time;
    }

    public Node(Node next){
        this.next = next;
    }

    public Node(){

    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public LongAdder getCount() {
        return count;
    }

    public void setCount(LongAdder count) {
        this.count = count;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addCount(){
        this.count.increment();
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", time=" + time +
                ", count=" + count +
                ", next=" + next +
                '}';
    }
}
