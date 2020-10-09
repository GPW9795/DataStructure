package com.mj;

import com.mj.map.Map;
import com.mj.map.TreeMap;
import com.mj.set.Set;
import com.mj.set.TreeSet;

public class Main {
    static void test1() {
        Map<String, Integer> map = new TreeMap<>();
        map.put("a", 2);
        map.put("c", 5);
        map.put("b", 6);
        map.put("c", 8);
        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });

    }

    static void test2(){
        Set<String> set = new TreeSet<>();
        set.add("d");
        set.add("b");
        set.add("a");
        set.add("c");

        set.traversal(new Set.Vistor<String>() {
            @Override
            public boolean visit(String element) {
                System.out.println(element);
                return false;
            }
        });
    }

    public static void main(String[] args) {
        test2();
    }
}
