package com.mj;

public class Main {

    public static void main(String[] args) {
        int[] array = {11, 22, 33};

        // new是向对堆空间申请内存
        ArrayList list = new ArrayList();
        list.add(99);
        list.add(88);
        list.add(77);
        list.add(66);
        list.add(55);
        System.out.println(list.toString());

        list.remove(2);
        System.out.println(list.toString());

        list.add(list.size(),44);
        System.out.println(list.toString());

        list.set(3,30);
//        Assert.test(list.get(3) == 30);

    }

}
