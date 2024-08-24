package com.talen.collection_.list_;

import java.util.ArrayList;
import java.util.HashSet;

public class ArrayListSource {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        ArrayList al = new ArrayList();
//        ArrayList al = new ArrayList(8);

        for (int i = 0; i < 10; i++) {
            al.add(i);
        }

        for (int i = 10; i < 15; i++) {
            al.add(i);
        }

        HashSet hashSet1 = new HashSet();
        hashSet1.add("fs");
    }
}
