package com.sampson.dataStructures.linkedlist;

import com.sampson.dataStructures.linkedlist.pojo.DoubleHeroNode;

/**
 * @author Sampson
 */
public class DoubleLinkedList {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        DoubleHeroNode hero1 = new DoubleHeroNode(1, "宋江", "及时雨");
        DoubleHeroNode hero2 = new DoubleHeroNode(4, "卢俊义", "玉麒麟");
        DoubleHeroNode hero3 = new DoubleHeroNode(5, "吴用", "智多星");
        DoubleHeroNode hero4 = new DoubleHeroNode(7, "林冲", "豹子头");
        DoubleLinked doubleLinked = new DoubleLinked();
        doubleLinked.list();

        doubleLinked.add(hero1);
        doubleLinked.add(hero2);
        doubleLinked.add(hero3);
        doubleLinked.add(hero4);
        doubleLinked.list();
        DoubleHeroNode hero5 = new DoubleHeroNode(7, "haode", "666");
        doubleLinked.update(hero5);
        doubleLinked.list();


    }

    static class DoubleLinked {
        private DoubleHeroNode head = new DoubleHeroNode(0, "", "");

        //返回头节点
        public DoubleHeroNode getHead() {
            return head;
        }

        /**
         * 遍历双向链表
         */
        public void list() {
            //判断链表是否为空
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            //因为头节点不能动，因此我们需要一个辅助变量来遍历
            DoubleHeroNode temp = head.next;
            while (temp != null) {
                //判断是否到链表的最后
                //输出节点的信息
                System.out.println(temp);
                //将temp后移，一定要小心
                temp = temp.next;
            }
        }

        /**
         * 添加操作
         *
         * @param heroNode
         */

        public void add(DoubleHeroNode heroNode) {
            //因为head节点不能动，因此我们需要一个辅助变量temp
            DoubleHeroNode temp = head;
            //遍历连表，找到最后
            while (true) {
                //找到链表的最后
                if (temp.next == null) {
                    break;
                }
                //如果没有找到最后，将temp后移
                temp = temp.next;
            }
            //当退出while循环时，temp就指向了链表的最后
            //将最后这个节点的next指向新的节点
            temp.next = heroNode;
            heroNode.pre = temp;

        }

        /***
         * 修改节点的信息，根据no编号来修改，即no编号不能改
         * @param heroNode
         */
        public void update(DoubleHeroNode heroNode) {
            //判断是否为空
            if (head.next == null) {
                System.out.println("链表为空~");
                return;
            }
            //找到需要修改的节点，根据no编号
            //定义一个辅助变量
            DoubleHeroNode temp = head.next;
            boolean flag = false;//表示是否找到该节点
            while (true) {
                if (temp == null) {
                    break;//已经遍历完链表
                }
                if (temp.no == heroNode.no) {
                    //找到
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            //根据flag判断是否找到要修改的节点
            if (flag) {
                temp.name = heroNode.name;
                temp.nickName = heroNode.nickName;
            } else {
                System.out.printf("没有找到 编号 %d 的节点，不能修改\n", heroNode.no);
            }
        }

        /**
         * 删除节点
         * 思路：
         * 1.对于双向链表我们可以直接找到要删除的节点
         * 2.找到后，自我删除即可
         *
         * @param no
         */

        public void del(int no) {
            //判断当前链表是否为空
            if (head.next == null) {
                System.out.println("链表为空，无法删除！");
                return;
            }
            DoubleHeroNode temp = head.next;
            boolean flag = false;//标志是否找到待删除节点的
            while (true) {
                if (temp == null) {//已经到链表的最后节点的next
                    break;
                }
                if (temp.no == no) {
                    //找到待删除节点的前一个节点temp
                    flag = true;
                    break;
                }
                temp = temp.next;//temp后移，遍历
            }
            //判断flag
            if (flag) {
                //找到，可以删除
                temp.pre.next = temp.next;
                //如果是删除的节点为最后一个节点，就不需要执行下面的语句，否则出现空指针
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
            } else {
                System.out.printf("要删除的 %d 节点不存在\n", no);
            }

        }

    }


}
