package com.talen.reflection;

import com.talen.Cat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/1
 * @time 8:38
 * 反射调用方法效率不高，通过关闭访问检查，稍稍优化
 */
public class Reflect02 {
    public static void main(String[] args) throws Exception {
        m1();
        m2();
        m3();
    }

    /**
     * 通过创建的对象实例来调用方法
     */
    public static void m1() {
        Cat cat = new Cat();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 900000000; i++) {
            cat.hi();
        }
        long end = System.currentTimeMillis();
        System.out.println("cat.hi耗时：" + (end - start));
    }

    /**
     * 通过创建的对象实例来调用方法
     */
    public static void m2() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> cls = Class.forName("com.talen.Cat");
        Object o = cls.newInstance(); // Java9开始弃用
        Method hiMethod = cls.getMethod("hi");

        long start = System.currentTimeMillis();
        for (int i = 0; i < 900000000; i++) {
            hiMethod.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("hiMethod耗时：" + (end - start));
    }

    /**
     * 通过创建的对象实例来调用方法
     */
    public static void m3() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> cls = Class.forName("com.talen.Cat");
        Object o = cls.newInstance();
        Method hiMethod = cls.getMethod("hi");
        hiMethod.setAccessible(true); // 反射调用方法时，取消访问检查

        long start = System.currentTimeMillis();
        for (int i = 0; i < 900000000; i++) {
            hiMethod.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("关闭访问检查 hiMethod耗时：" + (end - start));
    }
}
