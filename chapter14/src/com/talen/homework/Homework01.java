package com.talen.homework;

import java.util.ArrayList;
import java.util.Iterator;

public class Homework01 {
    public static void main(String[] args) {
        // 1.实例化两个News对象，只初始化标题
        // 2.将新闻对象添加到ArrayList集合中
        ArrayList newsList = new ArrayList();
        newsList.add(new News("新冠确诊病例超千万，数百万印度教信徒赴“圣河”沐浴引民众担忧"));
        newsList.add(new News("男子突然想起来两个月前钓的鱼还在网兜里，捞起一看赶紧放生"));

        // 3.倒叙遍历
        // 4.打印新闻标题，超过十五个字只保留15个，后加...
        for (int i = newsList.size() - 1; i >= 0 ; i--) {
            String headInfo = ((News) newsList.get(i)).getHead();
            if (headInfo.length() > 15) {
                headInfo = headInfo.substring(0, 15) + "...";
            }
            System.out.println(headInfo);
        }

        System.out.println("======使用Collections.reverse()方法后使用增强for循环");
        for(Object o : newsList) {
            String headInfo = ((News) o).getHead();
            if (headInfo.length() > 15) {
                headInfo = headInfo.substring(0, 15) + "...";
            }
            System.out.println(headInfo);
        }

        System.out.println("使用迭代器");
        Iterator iterator = newsList.iterator();
        while (iterator.hasNext()) {
            String headInfo = ((News) iterator.next()).getHead();
            if (headInfo.length() > 15) {
                headInfo = headInfo.substring(0, 15) + "...";
            }
            System.out.println(headInfo);
        }
    }
}

class News {
    private String head;
    private String content;

    public News(String head) {
        this.head = head;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "head='" + head + '\'' +
                '}';
    }
}