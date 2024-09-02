package com.talen.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/1
 * @time 8:17
 */
public class Reflect01 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        // 1.加载类，并返回该类的Class类对象
        Class<?> cls = Class.forName("com.talen.Cat");
        // 2.通过cls得到加载的类 com.talen.Cat 的对象实例
        Constructor<?>[] dc = cls.getDeclaredConstructors();//
        System.out.println("构造器有：" + Arrays.toString(dc)); // 运行类型
        Object o = cls.newInstance();
        System.out.println("o的运行类型是：" + o); // 运行类型
        // 3. 通过cls得到你加载的类的"hi"方法对象，即在反射中，可以把方法视为对象
        Method method1 = cls.getMethod("hi");
        // 4. 通过方法对象，来实现调用方法
        System.out.println("=========================");
        method1.invoke(o);

        // 5.getField, getFields只能获取public的属性
        Field fieldName = cls.getField("gender");
        System.out.println(fieldName.get(o));

        Field[] fields = cls.getFields();
        System.out.println("fields：" + Arrays.toString(fields));

        Class<?> clsDog = Class.forName("com.talen.reflection.Dog");
        System.out.println("clsDog.getFields(): " + Arrays.toString(clsDog.getFields()));

        // 6. 构造器对象
        Constructor<?> constructor = cls.getConstructor(); // 获得默认构造器对象
        System.out.println(constructor); // cat()
        constructor.newInstance();

        Constructor<?> constructor1 = cls.getConstructor(String.class);
        System.out.println(constructor1); // cat(String name)
        constructor1.newInstance("name");
    }

}
