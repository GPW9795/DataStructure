package com.mj.set;

import com.mj.list.LinkedList;
import com.mj.list.List;

public class ListSet<E> extends LinkedList<E> implements Set<E> {

    private List<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        int index = list.indexOf(element); // 获取元素索引
        if (index != list.ELEMENT_NOT_FOUND) { // 存在此元素则覆盖，还是去重
            list.set(index, element);
        } else { // 不存在则添加
            list.add(element);
        }
    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if (index != list.ELEMENT_NOT_FOUND) {
            list.remove(index);
        }
    }

    @Override
    public void traversal(Vistor<E> vistor) {
        if (vistor == null) {
            return;
        }

        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (vistor.visit(list.get(i))) return;
        }
    }
}
