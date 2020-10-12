package com.mj.map;


public class LinkedHashMap<K, V> extends HashMap<K, V> {
    private static class LinkedNode<K,V> extends Node<K,V> {
        private LinkedNode<K,V> first;
        private LinkedNode<K,V> last;

        public LinkedNode(K key, V value, Node<K, V> parent) {
            super(key, value, parent);
        }
    }
}
