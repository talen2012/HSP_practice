package com.talen.homework;

public class Homework02 {
    public static void main(String[] args) {
        /**
         * 调用方法验证用户名，密码和邮箱
         * 1. 用户名长度2-4
         * 2. 密码是6位，且全都是数字
         * 3. 邮箱包含@和. ,且@在.之前
         * 验证成功就提示注册成功，有问题就抛出异常，给出提示
         */
        String name = "abcd";
        String pwd = "123456";
        String email = "tal@163.com";
        try {
            userRegister(name, pwd, email);
            System.out.println("注册成功！");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void userRegister(String name, String pwd, String email) {
        if (name == null || pwd == null || email == null) {
            throw new RuntimeException("参数不能为空");
        }

        if (!(name.length() >= 2 && name.length() <= 4)) {
            throw new RuntimeException("用户名长度2-4");
        }

        if (pwd.length() != 6) {
            throw new RuntimeException("密码长度为6");
        }

        if (!isDgital(pwd)) {
            throw new RuntimeException("密码必须是数字");
        }

        if (!checkEmailAddress(email)) {
            throw new RuntimeException("邮箱包含@和. ,且@在.之前");
        }

    }

    public static Boolean isDgital(String str) {
        char[] c = str.toCharArray();
        for (char ci: c) {
            if (ci < '0' || ci > '9') {
                return false;
            }
        }
        return true;
    }

    public static Boolean checkEmailAddress(String email) {
        int i1 = email.indexOf("@");
        int i2 = email.indexOf(".");
        if (!(i1 > 0 && i2 > i1)) {
            return false;
        }
        return true;
    }
}
