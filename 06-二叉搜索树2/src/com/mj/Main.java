package com.mj;

import com.mj.printer.BinaryTrees;
import com.mj.tree.BST;


public class Main {

    public static void main(String[] args) {
        test1();
    }

    static void test1() {
        Integer[] data = {7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12};

        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        System.out.println(bst.contains(5));
        System.out.println(bst.contains(20));
    }
}
