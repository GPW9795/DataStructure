package com.mj;

import com.mj.map.LinkedHashMap;
import com.mj.map.Map;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Person p1 = new Person(10, 1.67f, "jack");
        Person p2 = new Person(10, 1.67f, "jack");

        Map<Object, Integer> map = new LinkedHashMap<>();
        map.put(p1, 1);
        map.put(p2, 2);
        map.put("jack", 3);
        map.put("rose", 4);
//        map.put("jack", 5);
        map.put(null, 6);

//        System.out.println(map.remove("jack"));
//        System.out.println(map.get("rose"));
//        System.out.println(map.get("jack"));
//        System.out.println(map.get(null));
//        System.out.println(map.get(p1));
        map.traversal(new Map.Visitor<Object, Integer>() {
            public boolean visit(Object key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    static void test1() {
        String string = "jack";
        System.out.println(string.hashCode());
        int len = string.length();
        int hashCode = 0;
        for (int i = 0; i < len; i++) {
            char c = string.charAt(i);
            hashCode = hashCode * 31 + c;
//            hashCode = (hashCode << 5) - hashCode + c;
        }
        System.out.println(hashCode);
    }

    static void test2() {
        Integer a = 100;
        Float b = 10.6f;
        Long c = 156l;
        Double d = 10.9;
        String e = "rose";

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        System.out.println(d.hashCode());
        System.out.println(e.hashCode());
    }
}
