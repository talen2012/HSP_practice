package com.talen.dao_.domain;

import java.util.Date;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/16
 * @time 11:42
 * 用于存储一条记录信息的类，对应actor表中的一条记录
 */
public class Actor {
    private Integer id; // java中，引用类型才有null，所以要使用包装类
    private String name;
    private String sex;
    private Date borndate;
    private String phone;

    public Actor() {} // 一定要给一个无参构造器[反射需要]

    public Actor(Integer id, String name, String sex, Date borndate, String phone) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.borndate = borndate;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBorndate() {
        return borndate;
    }

    public void setBorndate(Date borndate) {
        this.borndate = borndate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", borndate=" + borndate +
                ", phone='" + phone + '\'' +
                '}';
    }
}
