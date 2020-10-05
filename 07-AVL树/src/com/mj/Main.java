package com.mj;

import com.mj.printer.BinaryTrees;
import com.mj.tree.AVLTree;


public class Main {

    public static void main(String[] args) {
        test1();
    }

    static void test1() {
        Integer[] data = {7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12};

        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
        }

        BinaryTrees.println(avl);
        System.out.println(avl.contains(5));
        System.out.println(avl.contains(20));
    }
}
