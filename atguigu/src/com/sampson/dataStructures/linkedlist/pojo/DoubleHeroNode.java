package com.sampson.dataStructures.linkedlist.pojo;

import com.sampson.dataStructures.linkedlist.DoubleLinkedList;

/**
 * 双向链表实体类
 *
 * @author Sampson
 */
public class DoubleHeroNode {
    public int no;
    public String name;
    public String nickName;
    /**
     * 指向下一个节点，默认为null
     */
    public DoubleHeroNode next;
    /**
     * 指向前一个节点，默认为null
     */
    public DoubleHeroNode pre;

    public DoubleHeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "DoubleHeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
