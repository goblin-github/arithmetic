package com.sampson.dataStructures.queue;


import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) {
        //测试一把，创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        //接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.println("取出的数据是：" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.println("取出的数据是：" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }
        System.out.println("程序退出~~~");

    }

    /**
     * 基于数组实现的队列
     */
    static class ArrayQueue {
        private final int maxSize;//表示数组的最大容量
        private int front; //队列头
        private int rear;  //队列尾
        private final int[] arr; //该数组用于存放数据，模拟队列

        public ArrayQueue(int maxSize) {
            this.maxSize = maxSize;
            this.arr = new int[maxSize];
            this.front = -1; //指向队列头部，分析出front是指向队列头的前一个位置
            this.rear = -1; //指向队列尾，指向队列尾的数据（即就是队列最后一个数据）
        }

        //判断队列是否为空
        public boolean isEmpty() {
            return front == rear;
        }

        //判断队列满
        public boolean isFull() {
            return rear == maxSize - 1;
        }

        //添加数据到队列
        public void addQueue(int n) {
            //判断队列是否满
            if (isFull()) {
                System.out.println("队列满，不能加入数据~");
                return;
            }
            rear++;
            arr[rear] = n;
        }

        //获取队列的数据，出队列
        public int getQueue() {
            //判断队列是否为空
            if (isEmpty()) {
                throw new RuntimeException("队列空，不能取数据");
            }
            front++;
            return arr[front];
            //TODO 该处可根据实际需求来选择是否取出元素后将该位置进行置空
        }

        //显示队列所的所有数据
        public void showQueue() {
            if (isEmpty()) {
                System.out.println("队列为空，没有数据~~");
                return;
            }
            System.out.println("下面遍历队列所有数据~~~~~~~~~~~~");
            for (int value : arr) {
                System.out.println(value);
            }
            System.out.println("遍历完毕~~~~~~~~~~~~");

        }

        //显示队列的头数据，注意不是取出数据
        public int headQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空，没有数据~~");
            }
            return arr[front + 1];
        }
        //TODO 目前该数组使用一次后就不能用，没有达到复用的效果，需将其改进为环形队列
    }
}
