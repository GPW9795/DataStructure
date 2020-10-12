package com.mj;

import com.mj.heap.BinaryHeap;
import com.mj.printer.BinaryTrees;
import com.sun.source.tree.BinaryTree;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
//        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
//        BinaryHeap<Integer> heap = new BinaryHeap<>(data);
//        BinaryTrees.print(heap);
//        for (int i = 0; i < 30; i++) {
//            if (i==0){
//                System.out.print((int)(Math.random() * 100));
//            }else{
//                System.out.print("," + (int)(Math.random() * 100));
//            }
//        }
        test3();
    }

    static void test1() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(10);
        heap.add(90);
        heap.add(65);
        BinaryTrees.print(heap);

        heap.replace(5);
        System.out.println();
        BinaryTrees.print(heap);
    }

    static void test2() {
        // 新建小顶堆
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // 找出前k个最大的数
        int k = 5;
        Integer[] data = {66, 69, 40, 24, 19, 50, 47, 79, 53, 67,
                98, 75, 89, 41, 23, 20, 84, 3, 7, 98,
                3, 46, 90, 70, 56, 85, 43, 57, 90, 59};
        for (int i = 0; i < data.length; i++) {
            if (heap.size() < k) {
                heap.add(data[i]);
            } else if (data[i] > heap.get()) {
                heap.replace(data[i]);
            }
        }
        BinaryTrees.print(heap);
    }

    static void test3() {
        // 新建大顶堆
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        // 找出前k个最小的数
        int k = 5;
        Integer[] data = {66, 69, 40, 24, 19, 50, 47, 79, 53, 67,
                98, 75, 89, 41, 23, 20, 84, 3, 7, 98,
                3, 46, 90, 70, 56, 85, 43, 57, 90, 59};
        for (int i = 0; i < data.length; i++) {
            if (heap.size() < k) {
                heap.add(data[i]);
            } else if (data[i] < heap.get()) {
                heap.replace(data[i]);
            }
        }
        BinaryTrees.print(heap);
    }
}
