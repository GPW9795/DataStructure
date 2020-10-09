package com.mj;

import com.mj.set.ListSet;
import com.mj.set.Set;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new ListSet<>();

        set.add(10);
        set.add(11);
        set.add(12);
        set.add(10);

        set.traversal(new Set.Vistor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }
}
