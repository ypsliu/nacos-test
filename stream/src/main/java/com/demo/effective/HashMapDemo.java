package com.demo.effective;

import cn.hutool.core.lang.tree.Node;
import cn.hutool.core.lang.tree.TreeNode;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.K;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/13
 * Time: 10:41
 * Description: No Description
 */
public class HashMapDemo<K,V> {
    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        HashMapDemo.Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
    //这两个是限定值 当节点数大于8时会转为红黑树存储
    static final int TREEIFY_THRESHOLD = 8;
    //当节点数小于6时会转为单向链表存储
    static final int UNTREEIFY_THRESHOLD = 6;
    //红黑树最小长度为 64
    static final int MIN_TREEIFY_CAPACITY = 64;
    //HashMap容量初始大小
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    //HashMap容量极限
    static final int MAXIMUM_CAPACITY = 1 << 30;
    //负载因子默认大小
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    //Node是Map.Entry接口的实现类
    //在此存储数据的Node数组容量是2次幂
    //每一个Node本质都是一个单向链表
    transient Node<K,V>[] table;
    //HashMap大小,它代表HashMap保存的键值对的多少
    transient int size;
    //HashMap被改变的次数
    transient int modCount;
    //下一次HashMap扩容的大小
    int threshold;
    //存储负载因子的常量
    final float loadFactor;

    //默认的构造函数
    public HashMapDemo() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }
    //指定容量大小
    public HashMapDemo(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
    //指定容量大小和负载因子大小
    public HashMapDemo(int initialCapacity, float loadFactor) {
        //指定的容量大小不可以小于0,否则将抛出IllegalArgumentException异常
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        //判定指定的容量大小是否大于HashMap的容量极限
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        //指定的负载因子不可以小于0或为Null，若判定成立则抛出IllegalArgumentException异常
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);

        this.loadFactor = loadFactor;
        // 设置“HashMap阈值”，当HashMap中存储数据的数量达到threshold时，就需要将HashMap的容量加倍。
        //tableSizeFor用于查找到大于给定数值的最近2次幂值，比如给定18就是32。给定33就是64。
        this.threshold = tableSizeFor(initialCapacity);
    }
    //传入一个Map集合,将Map集合中元素Map.Entry全部添加进HashMap实例中
    public HashMapDemo(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        //此构造方法主要实现了Map.putAll()
/*
        putMapEntries(m, false);
*/
    }

    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    //HashMap.put的具体实现
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        //判定table不为空并且table长度不可为0,否则将从resize函数中获取
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        //这样写法有点绕,其实这里就是通过索引获取table数组中的一个元素看是否为Null
        //具体键值对在哈希表中的位置（数组 index）取决于下面的位运算：
        if ((p = tab[i = (n - 1) & hash]) == null)
            //若判断成立,则New一个Node出来赋给table中指定索引下的这个元素
            tab[i] = newNode(hash, key, value, null);
        else {  //若判断不成立
            Node<K,V> e; K k;
            //对这个元素进行Hash和key值匹配
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
//            else if (p instanceof TreeNode) //如果数组中德这个元素P是TreeNode类型
                //判定成功则在红黑树中查找符合的条件的节点并返回此节点
                /*e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);*/
            else { //若以上条件均判断失败，则执行以下代码
                //向Node单向链表中添加数据
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        //若节点数大于等于8
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            //转换为红黑树
                            /*treeifyBin(tab, hash);*/
                        break;
                    }
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e; //p记录下一个节点
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                /*afterNodeAccess(e);*/
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold) //判断是否需要扩容
            resize();
        /*afterNodeInsertion(evict);*/
        return null;
    }


    //重新设置table大小/扩容 并返回扩容的Node数组即HashMap的最新数据
    final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table; //table赋予oldTab作为扩充前的table数据
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            //判定数组是否已达到极限大小，若判定成功将不再扩容，直接将老表返回
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            //若新表大小(oldCap*2)小于数组极限大小 并且 老表大于等于数组初始化大小
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY)
                //旧数组大小oldThr 经二进制运算向左位移1个位置 即 oldThr*2当作新数组的大小
                newThr = oldThr << 1; // double threshold
        }
        //若老表中下次扩容大小oldThr大于0
        else if (oldThr > 0)
            newCap = oldThr;  //将oldThr赋予控制新表大小的newCap
        else { //若其他情况则将获取初始默认大小
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        //若新表的下表下一次扩容大小为0
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;  //通过新表大小*负载因子获取
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                    (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr; //下次扩容的大小
        @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab; //将当前表赋予table
        if (oldTab != null) { //若oldTab中有值需要通过循环将oldTab中的值保存到新表中
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {//获取老表中第j个元素 赋予e
                    oldTab[j] = null; //并将老表中的元素数据置Null
                    if (e.next == null) //若此判定成立 则代表e的下面没有节点了
                        newTab[e.hash & (newCap - 1)] = e; //将e直接存于新表的指定位置
//                    else if (e instanceof TreeNode)  //若e是TreeNode类型
                        //分割树，将新表和旧表分割成两个树，并判断索引处节点的长度是否需要转换成红黑树放入新表存储
//                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        Node<K,V> loHead = null, loTail = null; //存储与旧索引的相同的节点
                        Node<K,V> hiHead = null, hiTail = null; //存储与新索引相同的节点
                        Node<K,V> next;
                        //通过Do循环 获取新旧索引的节点
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        //通过判定将旧数据和新数据存储到新表指定的位置
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        //返回新表
        return newTab;
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
    Node<K,V> newNode(int hash, K key, V value, Node<K,V> next) {
        return new Node<>(hash, key, value, next);
    }


}
