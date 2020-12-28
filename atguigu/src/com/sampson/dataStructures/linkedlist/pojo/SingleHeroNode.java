package com.sampson.dataStructures.linkedlist.pojo;


/**
 * 单链表实体类
 *
 * @author Sampson
 */
public class SingleHeroNode {
    public int no;
    public String name;
    public String nickName;
    /**
     * 指向下一个节点
     */
    public SingleHeroNode next;

    public SingleHeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "SingleHerNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", next=" + next +
                '}';
    }
}
