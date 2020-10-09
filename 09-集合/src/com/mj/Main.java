package com.mj;

import com.mj.file.FileInfo;
import com.mj.file.Files;
import com.mj.set.ListSet;
import com.mj.set.Set;
import com.mj.set.TreeSet;

public class Main {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    static void test1() {
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

    static void test2() {
        Set<Integer> set = new TreeSet<>();

        set.add(11);
        set.add(10);
        set.add(12);
        set.add(10);
        set.add(1);
        set.add(7);
        set.add(9);

        set.traversal(new Set.Vistor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    static void test3() {
        FileInfo fileInfo = Files.read("C:\\Users\\MJ Lee\\Desktop\\src\\java\\util",
                new String[]{"java"});

        System.out.println("文件数量：" + fileInfo.getFiles());
        System.out.println("代码行数：" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词数量：" + words.length);

        Times.test("ListSet", new Times.Task() {
            public void execute() {
                testSet(new ListSet<>(), words);
            }
        });

        Times.test("TreeSet", new Times.Task() {
            public void execute() {
                testSet(new TreeSet<>(), words);
            }
        });
    }

    static void testSet(Set set, String[] words) {
        for (int i = 0; i < words.length; i++) {
            set.add(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            set.remove(words[i]);
        }
    }
}
