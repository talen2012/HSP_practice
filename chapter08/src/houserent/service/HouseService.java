package houserent.service;

import houserent.domain.House;

public class HouseService {
    // 创建一个房屋列表
    private House[] houses;
    private int idCnt = 0; // 一个全局整数，记录编号值，编号值是一直增加的
    private int houseNums = 0; // 一个全局整数，记录当前房屋列表中的房屋数

    public HouseService(int size) {
        houses = new House[size];
    }

    // 1. 打印房屋数据信息
    public void showList() {
        for (int i = 0; i < houseNums; i++) {
            System.out.println(houses[i]);
        }
    }

    // 2. 添加房屋信息
    public boolean add(House newHouse) {
        if (houseNums == houses.length) {
            System.out.println("列表已满！无法再添加房屋信息");
            return false;
        }

        houses[houseNums++] = newHouse;
        // 每添加一个房屋信息，id加1，并且要赋给该新增房屋的id属性
        newHouse.setId(++idCnt);
        return true;
    }

    // 3. 删除指定编号的房屋
    public boolean del(int delId) {
        int index = -1;
        // 查找房屋所在的数组索引
        for (int i = 0; i < houseNums; i++) {
            if (delId == houses[i].getId()) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("未找到该房屋！");
            return false;
        }

        // 删除该房屋，具体是将后续的房屋信息往前挪一位
        for (int i = index; i < houseNums - 1 ; i++) {
            houses[i] = houses[i + 1];
        }

        // 最后一个房屋信息置为null
        houses[--houseNums] = null; // 注意houseNums-1才是最后一个房屋的索引，所以这里要先自减
        return true;
    }

    // 4. 查找指定编号的房屋信息
    // 由于这个功能比较常用，考虑到后续修改房屋信息的需要，这里将找到的房屋对象返回，而不是仅仅返回是否找到
    public House findById(int searchId) {
        for (int i = 0; i < houseNums; i++) {
            if (searchId == houses[i].getId()) {
                return houses[i];
            }
        }
        System.out.println("未找到该房屋信息！");
        return null;
    }

    // 5. 修改指定编号房屋的信息
    public boolean updateById(House houseFound, String owner, String phone, String address, int rent, String status) {
        if (houseFound == null) {
            return false;
        }
        houseFound.setOwner(owner);
        houseFound.setPhone(phone);
        houseFound.setAddress(address);
        houseFound.setRent(rent);
        houseFound.setState(status);
        return true;
    }
}
