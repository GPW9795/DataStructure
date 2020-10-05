package com.mj;

import com.mj.printer.BinaryTrees;
import com.mj.tree.AVLTree;


public class Main {

    public static void main(String[] args) {
        test1();
    }

    static void test1() {
        Integer[] data = {9, 5, 3, 1, 2, 4, 7, 8, 11, 10, 6, 19, 21};

        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
        }

        BinaryTrees.println(avl);
        avl.add(25);
        BinaryTrees.println(avl);
        avl.remove(1);
        BinaryTrees.println(avl);
    }
}
