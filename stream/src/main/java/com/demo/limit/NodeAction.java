package com.demo.limit;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/17
 * Time: 17:18
 * Description: No Description
 */
public class NodeAction {
    /**
     * 删除尾部节点
     * @param sourceNode
     * @return
     */
    public static void removeEndNode(Node sourceNode){
        if(sourceNode.getNext() == null){
            System.out.println("need init windows!");
            return;
        }
        Node firstNode = sourceNode;
        while (firstNode.getNext() != null){
            if(firstNode.getNext().getNext()== null){
                //找到尾节点
                Node endNode = firstNode.getNext();
                endNode = null; //help GC
                firstNode.setNext(null);
                break;
            }
            firstNode = firstNode.getNext();
        }
    }

}
