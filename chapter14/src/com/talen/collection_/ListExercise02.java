package com.talen.collection_;

import java.util.LinkedList;
import java.util.List;

public class ListExercise02 {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
//        ArrayList list = new ArrayList();
        LinkedList list = new LinkedList();
//        Vector list = new Vector();

        list.add(new Book("三国演义", 50, "罗贯中"));
        list.add(new Book("红楼梦", 80, "曹雪芹"));
        list.add(new Book("水浒传", 30, "水浒传"));
        printList(list);

        bubbleSort(list);
        System.out.println("按价格从小到大排序后========");
        printList(list);
    }

    public static void bubbleSort(List list) {
        int listSize = list.size();
        for (int i = 0; i < listSize - 1; i++) {
            for (int j = 0; j < listSize - i -1; j++) {
                Book bookj = (Book)list.get(j);
                Book bookj2 = (Book)list.get(j + 1);
                if (bookj.getPrice() > bookj2.getPrice()) {
                    list.set(j, bookj2);
                    list.set(j + 1, bookj);
                }
            }
        }
    }

    public static void printList(List list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }
}

class Book {
    private String name;
    private double price;
    private String author;

    public Book(String name, double price, String author) {
        this.name = name;
        this.price = price;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return  "名称：" + name + "\t" +
                "价格：" + price + '\t' +
                "作者：" + author;
    }
}
