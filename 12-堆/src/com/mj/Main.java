package com.mj;

import com.mj.heap.BinaryHeap;
import com.mj.printer.BinaryTrees;

public class Main {

    public static void main(String[] args) {
        int[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
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
}
