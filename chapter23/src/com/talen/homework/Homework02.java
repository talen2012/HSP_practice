package com.talen.homework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/1
 * @time 16:39
 */
public class Homework02 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> fileCls = Class.forName("java.io.File");

        Constructor<?>[] declaredConstructors = fileCls.getDeclaredConstructors();

        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println("File的所有构造器：" + declaredConstructor);
        }

        // 创建file对象
        Constructor<?> constructor = fileCls.getConstructor(String.class);
        Object fileObj = constructor.newInstance("chapter23/src/myNew.txt");

        // 调用createNewFile()方法
        Method createNewFileMethod = fileCls.getMethod("createNewFile");
        createNewFileMethod.invoke(fileObj);

    }
}
