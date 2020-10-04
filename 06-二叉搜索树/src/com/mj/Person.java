package com.mj;

import com.mj.printer.BinaryTreeInfo;

public class Person implements Comparable<Person> {
    private int age;

    public Person(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person e) {
        return this.age - e.age;
    }

    @Override
    public String toString() {
        return "age=" + this.age;
    }
}
