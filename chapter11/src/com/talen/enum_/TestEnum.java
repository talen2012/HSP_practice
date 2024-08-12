package com.talen.enum_;

public class TestEnum {
    public static void main(String[] args) {
        Season[] seasons = Season.values();
        for (Season season : seasons) {
            System.out.println(season);
        }
    }
}

enum Season {
    // 这里没有写name属性，但enum关键字，默认继承了Enum, 父类中有name属性，但是是private的
    SPRING("一月","温暖"), SUMMER("四月","炎热");
    private String desc;
    private String name;

    // 1. 构造器私有化
    private Season(String name, String desc) {
        this.name = name;        this.desc = desc;
    }

    @Override
    public String toString() {
        return name;
    }
}
