package com.mj;

import com.mj.circle.CircleLinkedList;
import com.mj.circle.SingleCircleLinkedList;
import com.mj.single.SingleLinkedList2;

public class Main {

    public static void main(String[] args) {
//        TimeTool.check("动态数组", new TimeTool.Task() {
//            @Override
//            public void execute() {
//                testList(new ArrayList<>());
//            }
//        });
//
//        TimeTool.check("双向链表", new TimeTool.Task() {
//            @Override
//            public void execute() {
//                testList(new LinkedList<>());
//            }
//        });
//        testList(new SingleCircleLinkedList<>());
//        testList(new CircleLinkedList<>());
        josephus();
    }

    static void testList(List<Integer> list) {
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        list.add(0, 55);
        list.add(2, 66);
        list.add(list.size(), 77);

        list.remove(0);
        list.remove(2);
        list.remove(list.size() - 1);

        Asserts.test(list.indexOf(44) == 3);
        Asserts.test(list.indexOf(22) == List.ELEMENT_NOT_FOUND);
        Asserts.test(list.contains(33));
        Asserts.test(list.get(0) == 11);
        Asserts.test(list.get(1) == 66);
        Asserts.test(list.get(list.size() - 1) == 44);

        System.out.println(list);
    }

    static void josephus(){
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        for (int i = 1; i <= 8; i++) {
            list.add(i);
        }
        // 指向头节点
        list.reset();

        while (!list.isEmpty()){
            list.next();
            list.next();
            System.out.println(list.remove());
        }

    }

}
