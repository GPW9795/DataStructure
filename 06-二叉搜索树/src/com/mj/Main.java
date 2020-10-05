package com.mj;

import com.mj.printer.BinaryTreeInfo;
import com.mj.printer.BinaryTrees;

import java.util.Comparator;
import java.util.Map;

public class Main {

    private static class PersonComparator1 implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    private static class PersonComparator2 implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }

    public static void main(String[] args) {
        test1();
    }

    static void test1() {
        Integer[] data = {7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12};

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        System.out.println(bst.contains(5));
        System.out.println(bst.contains(20));

//        bst.preorderTraversal();
//        bst.inorderTraversal();
//        bst.postorderTraversal();
//        bst.levelOrderTraversal();
//        System.out.println(bst);
//        bst.remove(7);
//        BinaryTrees.println(bst);
//        bst.remove(12);
//        BinaryTrees.println(bst);
//        bst.remove(11);
//        BinaryTrees.println(bst);
    }

    static void test2() {
        Integer[] data = {7,4,9,2,5};
        BinarySearchTree<Person> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(new Person(data[i]));
        }
        BinaryTrees.println(bst);
        System.out.println(bst.isComplete());

//        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator2());
//        for (int i = 0; i < data.length; i++) {
//            bst2.add(new Person(data[i]));
//        }
//        BinaryTrees.println(bst2);
    }

    static void test3() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < 10; i++) {
            bst.add((int) (Math.random() * 100));
        }
        BinaryTrees.println(bst);
        System.out.println(bst.isComplete());
    }

}
