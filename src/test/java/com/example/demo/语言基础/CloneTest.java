package com.example.demo.语言基础;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XZQ
 * @Date 2021/7/10 09:41
 **/
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        A a=new A();
        a.setAge(10);
        a.setName("张三");
        a.books.add("语文");
        A b= (A) a.clone();
        a.hashcode();
        b.hashcode();
        System.out.println(a.equals(b));
        System.out.println(a==b);
        System.out.println(b.toString());
        b.setName("李四");
        b.books.add("数学");
        System.out.println(a.toString());

    }
}

class A implements Cloneable{
    private String name;
    private int age;
    List<String> books=new ArrayList<String>();
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", books=" + books +
                '}';
    }
    public void hashcode(){
        System.out.println(super.hashCode());
    }
}
