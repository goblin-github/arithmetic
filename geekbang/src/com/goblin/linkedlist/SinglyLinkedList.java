package com.goblin.linkedlist;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2022/12/12
 * <p>
 * 1）单链表的插入、删除、查找操作；
 * 2）链表中存储的是int类型的数据；
 */
public class SinglyLinkedList {

    private Node head = null;

    /**
     * 根据值查找对应node节点
     *
     * @param value 值
     * @return node
     */
    public Node findByValue(int value) {
        Node p = head;
        while (p != null && p.getData() != value) {
            p = p.next;
        }
        return p;
    }

    /**
     * 根据索引查找对应node节点
     *
     * @param index 索引
     * @return node
     */
    public Node findByIndex(int index) {
        Node p = head;
        int pos = 0;
        while (p != null && pos != index) {
            p = p.next;
            ++pos;
        }
        return p;
    }

    /**
     * 链表头部插入
     *
     * @param value 待插入节点value值
     */
    public void insertToHead(int value) {
        insertToHead(createNode(value));
    }


    /**
     * 链表尾部插入
     *
     * @param value 待插入节点value值
     */
    public void insertTail(int value) {
        Node newNode = createNode(value);
        //空链表，可以插入新节点作为head，也可以不操作
        if (head == null) {
            head = newNode;
        } else {
            Node q = head;
            while (q.next != null) {
                q = q.next;
            }
            newNode.next = null;
            q.next = newNode;
        }
    }

    /**
     * 链接指定节点之后插入
     *
     * @param p     指定节点
     * @param value 待插入值
     */
    public void insertAfter(Node p, int value) {
        Node newNode = createNode(value);
        if (p == null) {
            return;
        }
        newNode.next = p.next;
        p.next = newNode;
    }

    /**
     * 链表指定节点之前插入
     *
     * @param p     指定节点
     * @param value 待插入值
     */
    public void insertBefore(Node p, int value) {
        Node newNode = createNode(value);
        if (p == null) {
            return;
        }
        if (head == p) {
            insertToHead(newNode);
            return;
        }
        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }
        if (q == null) {
            return;
        }
        newNode.next = p;
        q.next = newNode;
    }

    /**
     * 删除当前节点
     *
     * @param p 待删除节点
     */
    public void deleteByNode(Node p) {
        if (p == null || head == null) {
            return;
        }
        if (p == head) {
            head = head.next;
            return;
        }
        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }
        if (q == null) {
            return;
        }
        q.next = q.next.next;
    }

    /**
     * 根据值删除对应节点
     *
     * @param value 待删除节点值
     */
    public void deleteByValue(int value) {
        if (head == null) {
            return;
        }
        Node p = head;
        Node q = null;
        while (p != null && p.getData() != value) {
            q = p;
            p = p.next;
        }

        if (p == null) {
            return;
        }

        if (q == null) {
            head = head.next;
        } else {
            q.next = q.next.next;
        }

        // 可重复删除指定value的代码
        /*
           if (head != null && head.data == value) {
           head = head.next;
           }
           Node pNode = head;
           while (pNode != null) {
           if (pNode.next.data == data) {
           pNode.next = pNode.next.next;
           continue;
           }
           pNode = pNode.next;
           }
         */
    }

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.getData() + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * 判断左右节点是否对应相等
     *
     * @param left  左节点
     * @param right 右节点
     * @return boolean
     */
    private boolean trueOrFalseResult(Node left, Node right) {
        Node l = left;
        Node r = right;
        boolean flag = true;
        while (l != null && r != null) {
            if (l.data == r.data) {
                l = l.next;
                r = r.next;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 判断是否为回文
     *
     * @return boolean
     */
    public boolean palindrome() {
        if (head == null) {
            return false;
        } else {
            Node p = head;
            Node q = head;
            if (p.next == null) {
                System.out.println("只有一个元素");
                return true;
            }
            while (q.next != null && q.next.next != null) {
                p = p.next;
                q = q.next.next;
            }
            Node leftLink = null;
            Node rightLink = null;
            if (q.next == null) {
                //　p 一定为整个链表的中点，且节点数目为奇数
                rightLink = p.next;
                leftLink = inverseLinkList(p).next;
            } else {
                //p q　均为中点
                rightLink = p.next;
                leftLink = inverseLinkList(p);
            }
            return trueOrFalseResult(leftLink, rightLink);

        }
    }

    //带结点的链表翻转
    public Node inverseLinkListHead(Node p) {
        //　Head　为新建的一个头结点
        Node head = new Node(9999, null);
        // p　为原来整个链表的头结点,现在Head指向　整个链表
        head.next = p;
        /*
        带头结点的链表翻转等价于
        从第二个元素开始重新头插法建立链表
        */
        Node Cur = p.next;
        p.next = null;
        Node next = null;

        while (Cur != null) {
            next = Cur.next;
            Cur.next = head.next;
            head.next = Cur;
            System.out.println("first " + head.getData());
            Cur = next;
        }
        //　返回左半部分的中点之前的那个节点
        //　从此处开始同步像两边比较
        return head;

    }

    /**
     * 无头结点的链表翻转
     *
     * @param p 待反转节点
     * @return 节点
     */
    private Node inverseLinkList(Node p) {
        Node pre = null;
        Node r = head;
        Node next;
        while (r != p) {
            next = r.next;
            r.next = pre;
            pre = r;
            r = next;
        }
        r.next = pre;
        //返回左半部分的中点之前的那个节点
        return r;

    }

    private void insertToHead(Node newNode) {
        if (head != null) {
            newNode.next = head;
        }
        head = newNode;
    }

    public static Node createNode(int value) {
        return new Node(value, null);
    }

    public static class Node {
        private final int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    public static void main(String[] args) {

        SinglyLinkedList link = new SinglyLinkedList();
        //int data[] = {1};
        //int data[] = {1,2};
        //int data[] = {1, 2, 3, 1};
        //int data[] = {1,2,5};
        int data[] = {1, 2, 2, 1};
        //int data[] = {1, 2, 5, 2, 1};
        //int[] data = {1, 2, 5, 3, 1};

        for (int i = 0; i < data.length; i++) {
            link.insertTail(data[i]);
        }
        // Node p = link.inverseLinkList_head(link.head);
        // while(p != null){
        //     System.out.println("aa"+p.data);
        //     p = p.next;
        // }

        System.out.println("打印原始:");
        link.printAll();
        if (link.palindrome()) {
            System.out.println("回文");
        } else {
            System.out.println("不是回文");
        }
    }

}
