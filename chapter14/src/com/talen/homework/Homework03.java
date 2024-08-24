package com.talen.homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Homework03 {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        Map m = new HashMap();
        m.put("jack", 650);
        m.put("tom", 1200);
        m.put("smith", 2900);

        // jack的工资更改为2600
        m.put("jack", 2600);
        // 遍历所有员工
        System.out.println("jack的工资更改为2600");
        Set entrySet = m.entrySet();
        for(Object o : entrySet) {
            Map.Entry entry = (Map.Entry) o;
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
        // 所有员工加薪100
        Set keySet = m.keySet();
        for(Object o : keySet) {
          m.put(o, ((Integer) m.get(o)) + 100);

        }
        // 遍历所有员工
        System.out.println("======所有员工工资加100");
        for(Object o: keySet) {
            System.out.println(o + "-" + m.get(o));
        }

        // 遍历所有工资
        System.out.println("======遍历所有工资");
        for(Object o : m.values()) {
            System.out.println(o);
        }
    }
}

