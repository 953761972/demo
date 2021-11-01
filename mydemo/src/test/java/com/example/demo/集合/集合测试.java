package com.example.demo.集合;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

/**
 * @Author XZQ
 * @Date 2021/4/10 15:02
 **/
public class 集合测试 {
    public static void main(String[] args) {
        //测试hashmap
        Map<String,String> m=new HashMap();
        m.put("2","b");
        m.put("3","b");
        m.put("4","b");
        m.put("5","b");
        m.put("6","b");
        m.put("7","b");
        m.put("8","b");
        m.put("9","b");
        m.put("10","b");
        m.put("11","b");
        m.put("12","b");
        m.put("13","b");
        m.put("14","b");
        m.put("15","b");
        m.put("16","b");
        m.put("17","b");
        m.computeIfAbsent("81", new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        });
        m.forEach((a,b)-> System.out.println(a+""+b));

        for(Map.Entry<String,String> mm:m.entrySet()){
            System.out.println(mm.getKey()+""+mm.getValue());
        }
        Collections.synchronizedMap(m);


    }
    @Test
    //测试ArrayList和LinkedList打印顺序
    public void ArrayListTest(){
        List l=new ArrayList<>();
        List l2=new LinkedList();
        l.add(1);
        l.add(8);
        l.add(4);
        l.add(2,9);
        l.forEach(a-> System.out.println(a));
        l2.add(1);
        l2.add(8);
        l2.add(4);
        l2.add(2,9);
        l2.forEach(a-> System.out.println(a));

    }

    @Test
    //测试computeIfAbsent
    public void mainTest(){
        int key=1;
        Map<Integer,List<Integer>> map=new HashMap<>();
        map.computeIfAbsent(key,v->new ArrayList<>()).add(1);//不存在则创建
        map.computeIfAbsent(key,v->new ArrayList<>()).add(1);//存在则返回
        map.computeIfAbsent(key, new Function<Integer, List<Integer>>() {
            @Override
            public List<Integer> apply(Integer integer) {
                return new ArrayList<>();
            }
        }).add(2);
        System.out.println(map.get(key));
        map.get(key).add(1);
        System.out.println(map.get(key));
    }
    @Test
    //ArrayList的remove
    public void testArrayList(){
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<6;i++){
            list.add(i);
        }
        System.out.println(list);

        Integer removeindex=4;//移除的是Integer这个对象，如果没有则返回false
        list.remove(removeindex);
        //System.out.println(list.get(removeindex));
        System.out.println(list);

        int removeindex2=4;//移除的是索引为4的这个元素，如果索引不存在则报错：IndexOutOfBoundsException
        list.remove(removeindex2);
        System.out.println(list);

    }

    @Test
    //CopyOnWriteArrayList
    public void testCopyOnWriteArrayList(){
        CopyOnWriteArrayList<Integer> list=new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);

        Iterator<Integer> iterator=list.iterator();
        while(iterator.hasNext()){
            Integer next=iterator.next();
            if(next==1)
                iterator.remove();//CopyOnWriteArrayLis的remove是直接失败的
        }
        System.out.println(list);
    }
    @Test
    //forEach的特性
    public void testMap(){
        List<Integer> l=new ArrayList();
        l.add(1);
        l.add(2);
        l.add(3);
        l.stream().forEach(a-> {
            if (a==1){
                return;}
            System.out.println(a);
        });
        System.out.println("");
        l.forEach(a-> {
            if (a==1){
                return;
            }
            System.out.println(a);
        });

    }
}
