package com.sampson.dataStructures.linkedlist;

import sun.jvm.hotspot.memory.HeapBlock;

import java.util.Stack;

/**
 * @Description: 单链表的实现
 * @Date: 2020-12-7 21:45
 */
public class SingleLinkedList {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(4, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(5, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(7, "林冲", "豹子头");
        SingleLinked singleLinked = new SingleLinked();
        HeroNode hero5 = new HeroNode(4, "44", "及时雨");
        HeroNode hero6 = new HeroNode(2, "22", "玉麒麟");
        HeroNode hero7 = new HeroNode(8, "88", "智多星");
        SingleLinked singleLinked2 = new SingleLinked();
        //加入
        // singleLinked.add(hero1);
        // singleLinked.add(hero4);
        // singleLinked.add(hero2);
        // singleLinked.add(hero3);
        ////加入按照编号的顺序
        singleLinked.addByOrder(hero1);
        singleLinked.addByOrder(hero4);
        singleLinked.addByOrder(hero2);
        singleLinked.addByOrder(hero3);
        singleLinked2.addByOrder(hero5);
        singleLinked2.addByOrder(hero6);
        singleLinked2.addByOrder(hero7);
        //显示一把
        //singleLinked.list();
       // singleLinked2.list();
        merge(singleLinked.head,singleLinked2.head);
        //测试修改节点的代码
       // HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        //singleLinked.update(newHeroNode);
        //System.out.println("修改后的链表情况~~");
   //     singleLinked.list();
        //删除一个节点
//        singleLinked.del(1);
//        singleLinked.del(4);
//        System.out.println("删除后的链表情况~~");
//        singleLinked.list();
    }

    /**
     * 定义SingleLiked，管理我们的英雄
     */
    static class SingleLinked {
        //先初始化一个头节点，头节点不要动，不存放具体的数据
        private HeroNode head = new HeroNode(0, "", "");

        /**
         * 添加节点到单向链表
         * 思路：
         * 1.找到当前链表的最后节点
         * 2.将最后这个节点的next指向新的节点
         *
         * @param heroNode
         */
        public void add(HeroNode heroNode) {
            //因为head节点不能动，因此我们需要一个辅助变量temp
            HeroNode temp = head;
            //遍历连表，找到最后
            while ((true)) {
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

        }

        /**
         * 第二种方式添加英雄时，根据排名将英雄插入到指定位置
         * （如果有这个排名，则添加失败，并给出提示）
         *
         * @param heroNode
         */
        public void addByOrder(HeroNode heroNode) {
            //因为头节点不能动，因此我们仍然通过一个辅助变量来帮助找到添加的位置
            //因为单链表，一次我们找的temp是位于添加位置的前一个节点，否则插入不了
            HeroNode temp = head;
            boolean flag = false; //flag标志添加的编号是否存在，默认为false
            while (true) {
                if (temp.next == null) {//说明temp已经在链表的最后
                    break;
                }
                if (temp.next.no > heroNode.no) {//位置找到，就在temp的后面插入
                    break;
                } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNoded的编号已然存在
                    flag = true;
                    break;
                }
                temp = temp.next;//后移，遍历当前链表
            }
            //判断flag的值
            if (flag) {//不能添加，说明编号存在
                System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
            } else {
                //插入到链表中，temp的后面
                heroNode.next = temp.next;
                temp.next = heroNode;
            }

        }

        /***
         * 修改节点的信息，根据no编号来修改，即no编号不能改
         * @param heroNode
         */
        public void update(HeroNode heroNode) {
            //判断是否为空
            if (head.next == null) {
                System.out.println("链表为空~");
                return;
            }
            //找到需要修改的节点，根据no编号
            //定义一个辅助变量
            HeroNode temp = head.next;
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
         * 1.head不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
         * 2.说明我们在比较时，是temp.next.no和需要删除的节点的no比较
         *
         * @param no
         */

        public void del(int no) {
            HeroNode temp = head;
            boolean flag = false;//标志是否找到待删除节点的
            while (true) {
                if (temp.next == null) {//已经到链表的最后
                    break;
                }
                if (temp.next.no == no) {
                    //找到待删除节点的前一个节点temp
                    flag = true;
                    break;
                }
                temp = temp.next;//temp后移，遍历
            }
            //判断flag
            if (flag) {
                //找到，可以删除
                temp.next = temp.next.next;
            } else {
                System.out.printf("要删除的 %d 节点不存在\n", no);
            }

        }

        //显示链表【遍历】
        public void list() {
            //判断链表是否为空
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            //因为头节点不能动，因此我们需要一个辅助变量来遍历
            HeroNode temp = head.next;
            while (true) {
                //判断是否到链表的最后
                if (temp == null) {
                    break;
                }
                //输出节点的信息
                System.out.println(temp);
                //将temp后移，一定要小心
                temp = temp.next;
            }
        }

        /**
         * 面试题：获取单链表的节点的个数（如果是带头结点的链表，需求不统计头节点）
         *
         * @param head
         * @return
         */
        public int getLength(HeroNode head) {
            if (head.next == null) {//空链表
                return 0;
            }
            int length = 0;
            //定义一个辅助变量，这里我们没有同意头节点
            HeroNode cur = head.next;
            while (cur != null) {
                length++;
                cur = cur.next;//遍历
            }
            return length;
        }

        /**
         * 新浪面试题：查找单链表中的倒数第k个结点
         * 思路：
         * 1.编写一个方法，接收head结点，同时接收一个index
         * 2.index表示是倒数第index个结点
         * 3.先把链表从头到尾遍历，得到链表的总长度getLength
         * 4.得到size后，我们从链表的第一个开始遍历（size-index）个，就可以得到
         * 5.如果找到了就返回该节点，否则返回null
         *
         * @param head
         * @param index
         * @return
         */
        public HeroNode findLastIndexNode(HeroNode head, int index) {
            //判断如果链表为空，返回null
            if (head.next == null) {
                return null;
            }
            //第一次遍历得到链表的长度（节点个数）
            int length = getLength(head);
            //第二次遍历（size-index）位置，就是我们倒数的第k个结点
            //先做一个index的校验
            if (index > length || index <= 0) {
                return null;
            }
            //定义辅助变量。for循环定位到倒数的index
            HeroNode cur = head.next;
            for (int i = 0; i < length - index; i++) {
                cur = cur.next;
            }
            return cur;

        }

        /**
         * 腾讯面试题：单链表的反转
         *
         * @param head
         */
        public void reversetList(HeroNode head) {
            //如果当前链表为空，或者只有一个节点，无需反转，直接返回
            if (head.next == null || head.next.next == null) {
                return;
            }
            //定义一个辅助的指针（变量），帮助我们遍历原来的链表
            HeroNode cur = head.next;
            HeroNode next = null;//指向当前节点[cur]的下一个节点
            HeroNode reverseHead = new HeroNode(0, "", "");
            //遍历原来的链表，每遍历一个节点就将其取出，并放在新的链表的最前端
            while (cur != null) {
                next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
                cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
                reverseHead.next = cur;//将cur连接到新的链表上
                cur = next;//让cur后移
            }
            //将head.next指向reverseHead.next，实现单链表的反转
            head.next = reverseHead.next;
        }
        /**
         * 百度面试题：从尾到头打印单链表（要求：方式1.反向遍历   方式2.Stack栈）
         * 方式1：先将单链表进行反转操作然后再遍历打印即可，这样做会破坏原来的单链表的结构，不建议
         * 方式2：可以利用栈这个数据结构，将各个节点压入栈中，然后利用栈的先进后出的特点，实现逆序打印
         *
         * @param head
         */
        public void reversePrint(HeroNode head) {
            if (head.next == null) {
                return;//空链表，不能打印
            }
            //创建一个栈，将各个节点压入栈
            Stack<HeroNode> stack = new Stack<>();
            HeroNode cur = head.next;
            while (cur != null) {
                HeroNode push = stack.push(cur);
                cur = cur.next;
            }
            //将栈中的节点进行打印
            while (stack.size() > 0) {
                System.out.println(stack.pop());
            }

        }


    }
    /**
     * 面试题：合并两个有序的单链表，合并和的新链表依然有序
     * @param head1
     * @param head2
     */
    public static HeroNode merge(HeroNode head1, HeroNode head2) {
        //先做判断，这里分为三种情况，链表1为空2不为空；链表1不为空2为空；链表12都为空
        if (head1.next == null) {
            if (head2.next == null) {
                return null;
            }
            return head2;
        } else {
            if (head2.next == null) {
                return head1;
            }
        }
        //定义一个新的链表
        HeroNode newHead = new HeroNode(0, "", "");
        //定义一个变量来记录新的链表的头节点，用来方法的返回
        HeroNode mergeHead = newHead;
        HeroNode next = head1.next;
        HeroNode next1 = head2.next;
        while (next != null && next1 != null) {
            if (next.no <= next1.no) {
                mergeHead.next = next;
                next = next.next;
            } else {
                mergeHead.next = next1;
                next1 = next1.next;
            }
            mergeHead = mergeHead.next;
        }
        //如果有任何一个链表为空，那么就把其余的链表全部放在新链表的最后
        mergeHead.next=next==null?next1:next;
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        SingleLinked linked = new SingleLinked();
        linked.head=newHead;
        linked.list();
        return newHead;
    }



    //定义HeroNode，每个HeadNode对象就是一个节点
    static class HeroNode {
        private int no;
        private String name;
        private String nickName;
        private HeroNode next;//指向下一个节点

        public HeroNode(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName +
                    '}';
        }
    }
}
