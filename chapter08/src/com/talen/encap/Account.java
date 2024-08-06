package com.talen.encap;

/**
 * Account 类要求具有属性：姓名（长度为 2 位 3 位或 4 位）、余额(必须>20)、
 * 密码（必须是六位）, 如果不满足，则给出提示信息，并给默认值(程序员自己定)
 */
public class Account {
    private String name;
    private String passwd;
    private double balance;

    // 构造器

    public Account() {
    }

    public Account(String name, String passwd, double balance) {
        setName(name);
        setPasswd(passwd);
        setBalance(balance);
    }
    
    // setter and getter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() <=4 && name.length() >= 2) {
            this.name = name;
        } else {
            System.out.println("你设置的名字有误，长度应在2-4，设为默认值：无名");
            this.name = "无名";
        }
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        if (passwd.length() ==6) {
            this.passwd = passwd;
        } else {
            System.out.println("你设置的密码长度有误，应为6，设为默认值：666666");
            this.passwd = "66666";
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance > 20.0) {
            this.balance = balance;
        } else {
            System.out.println("你设置的余额大小有误，最少为20.0，保持默认值：0.0");
        }
    }

    public String info() {
        return "name=" + name + " passwd=" + passwd + " balance=" + balance;
    }
}
