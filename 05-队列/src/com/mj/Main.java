package com.mj;

import com.mj.circle.CircleDeque;
import com.mj.circle.CircleQueue;

public class Main {

    public static void main(String[] args) {
        test2();
        test3();
    }

    static void test1() {
//        Queue<Integer> queue = new Queue<>();
//        queue.enQueue(11);
//        queue.enQueue(22);
//        queue.enQueue(33);
//        queue.enQueue(44);
//        queue.enQueue(55);
//
//        while (!queue.isEmpty()) {
//            System.out.println(queue.deQueue());
//        }

        Deque<Integer> queue = new Deque<>();
        queue.enQueueFront(11);
        queue.enQueueFront(22);
        queue.enQueueRear(33);
        queue.enQueueRear(44); // 队尾 44 33 11 22 队头

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueueRear());
        }

    }

    static void test2() {
        CircleQueue<Integer> queue = new CircleQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        for (int i = 0; i < 5; i++) {
            queue.deQueue();
        }
        for (int i = 0; i < 14; i++) {
            queue.enQueue(i);
        }
        System.out.println(queue.toString());
    }

    static void test3() {
        CircleDeque<Integer> queue = new CircleDeque<>();
        for (int i = 0; i < 10; i++) {
            queue.enQueueFront(i+1);
            queue.enQueueRear(i+100);
        }
        for (int i = 0; i < 3; i++) {
            queue.deQueueFront();
            queue.deQueueRear();
        }
        queue.enQueueFront(11);
        queue.enQueueFront(22);
        System.out.println(queue);
    }

}
