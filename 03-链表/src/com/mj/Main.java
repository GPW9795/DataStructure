package com.mj;

public class Main {

    public static void main(String[] args) {
        List<Integer> list2 = new LinkedList<>();
        list2.add(20);
        list2.add(0,10);
        list2.add(30);
        list2.add(list2.size(),40);
        System.out.println(list2);

        list2.remove(1);
        list2.remove(1);
        list2.remove(1);
        list2.remove(0);
        System.out.println(list2);
    }

}
