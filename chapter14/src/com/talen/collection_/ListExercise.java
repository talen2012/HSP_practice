package com.talen.collection_;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListExercise {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 12; i++) {
            list.add("talen" + i);
        }
        System.out.println("list = " + list);

        list.add(1, "韩顺平教育");
        System.out.println("list = " + list);

        System.out.println("第五个元素 = " + list.get(4));

        list.remove(5);
        System.out.println("list = " + list);

        list.set(6, "必胜");
        System.out.println("list = " + list);

        Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
