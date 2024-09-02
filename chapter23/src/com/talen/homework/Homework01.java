package com.talen.homework;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/1
 * @time 16:01
 */
public class Homework01 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // 1. 通过forName获得Class类对象
//        Class<?> cls = Class.forName("com.talen.homework.PrivateTest");
//        Constructor<?> constructor = cls.getConstructor(); // 这里只能返回public的构造函数，而默认构造函数时默认访问权限
//        Object privateTestObj = constructor.newInstance();
        // 2. 通过类.class获得Class类对象
        Class<PrivateTest> cls = PrivateTest.class;
        PrivateTest privateTestObj = cls.newInstance();


        // 修改私有属性name
        Field nameField = cls.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(privateTestObj, "talen");

        // 通过public方法getName获取name属性值
        Method getNameMethod = cls.getMethod("getName");
        System.out.println(getNameMethod.invoke(privateTestObj));
    }
}

class PrivateTest {
    private String name = "hellokitty";

//    public PrivateTest() {
//    }

    public String getName() {
        return name;
    }
}