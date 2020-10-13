package com.mj;


public class Main {

    public static void main(String[] args) {
        Trie<Integer> trie = new Trie<>();
        trie.add("cat", 1);
        trie.add("dog", 2);
        trie.add("doggy", 2);
        trie.add("catalog", 3);
        trie.add("cast", 4);
        trie.add("高沛雯", 5);
        System.out.println(trie.size());
        System.out.println(trie.get("cat"));
        System.out.println(trie.starsWith("ca"));
        System.out.println(trie.starsWith("gpw"));
        trie.remove("doggy");
        System.out.println(trie.starsWith("dog"));
    }

}
