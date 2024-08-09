package houserent.view;

import com.sun.source.doctree.UnknownInlineTagTree;
import houserent.domain.House;
import houserent.service.HouseService;
import houserent.utils.Utility;
import jdk.jshell.execution.Util;

/**
 * 1. 显示界面
 * 2. 接收用户的输入
 * 3. 调用HouseService完成对房屋信息的各种操作
 */
public class HouseView {
    private boolean loop = true;
    private HouseService houseService = new HouseService(5);

    // 为了调试方便，不用每次重新输入，先预输入三个房屋信息
    public HouseView() {
        houseService.add(new House("jack", "35363", "昌平区", 1500, "未出租"));
        houseService.add(new House("smith", "35463", "东城区", 2500, "已出租"));
        houseService.add(new House("scott", "56363", "西城区", 3000, "未出租"));
    }
    // 7. 修改房屋信息
    public void updateHouse() {
        System.out.println("------------------修改房屋信息------------------");
        System.out.print("要修改信息的房屋编号：");
        int updateId = Utility.readInt();
        House houseFound = houseService.findById(updateId);
        if (houseFound == null) {
            System.out.println("----------------修改房屋信息失败----------------");
        } else {
            System.out.println("请输入新的房屋信息，输入为空表示不修改相应属性");
            System.out.print("房主：");
            String owner = Utility.readString(8, houseFound.getOwner());
            System.out.print("电话：");
            String phone = Utility.readString(11, houseFound.getPhone());
            System.out.print("地址：");
            String address = Utility.readString(16, houseFound.getAddress());
            System.out.print("月租：");
            int rent = Utility.readInt(houseFound.getRent());
            System.out.print("状态：");
            String status = Utility.readString(3, houseFound.getState());
            houseService.updateById(houseFound, owner, phone, address, rent, status);
            System.out.println("修改后的房屋信息：");
            System.out.println("编号\t房主\t\t电话\t\t地址\t\t月租\t\t状态");
            System.out.println(houseFound);
            System.out.println("----------------修改房屋信息成功----------------");
        }
    }

    // 6. 通过ID查找房屋信息
    public void findHouse() {
        System.out.println("--------------------查找房屋--------------------");
        System.out.print("要查找的房屋编号：");
        int searchId = Utility.readInt();
        House houseFound = houseService.findById(searchId);
        if (houseFound == null) {
            System.out.println("------------------查找房屋失败------------------");
        } else {
            System.out.println("编号\t房主\t\t电话\t\t地址\t\t月租\t\t状态");
            System.out.println(houseFound);
            System.out.println("------------------查找房屋成功------------------");
        }
    }

    // 5. 删除房屋
    public void delHouse() {
        System.out.println("--------------------删除房屋--------------------");
        System.out.print("要删除的房屋编号：");
        int id = Utility.readInt();
        if (houseService.del(id)) {
            System.out.println("------------------删除房屋成功------------------");
        } else {
            System.out.println("------------------删除房屋失败------------------");
        }
    }

    // 4. 添加房屋
    public void addHouse() {
        System.out.println("--------------------添加房屋--------------------");
        System.out.print("房主：");
        String owner = Utility.readString(8);
        System.out.print("电话：");
        String phone = Utility.readString(11);
        System.out.print("地址：");
        String address = Utility.readString(16);
        System.out.print("月租：");
        int rent = Utility.readInt();
        System.out.print("状态：");
        String status = Utility.readString(3);
        House newHouse = new House(owner, phone, address, rent, status);
        if (houseService.add(newHouse)) {
            System.out.println("------------------添加房屋成功------------------");
        } else {
            System.out.println("------------------添加房屋失败------------------");
        }

    }

    // 3. 完成显示房屋列表
    public void showHouseList() {
        System.out.println("--------------------房屋列表--------------------");
        System.out.println("编号\t房主\t\t电话\t\t地址\t\t月租\t\t状态");
        houseService.showList();
        System.out.println("------------------房屋列表结束------------------");
    }

    // 2. 完成退出确认
    public void exit() {
        char c = Utility.readConfirmSelection();
        if (c == 'Y') {
            loop = false;
        }
        System.out.println("=============退出房屋出租系统============");
    }

    // 1. 显示主菜单，获取用户选择的功能编号，调用方法执行对应功能
    public void mainMenu() {
        do {
            System.out.println("\n=============房屋出租系统菜单============");
            System.out.println("\t\t\t1 新 增 房 源");
            System.out.println("\t\t\t2 查 找 房 屋");
            System.out.println("\t\t\t3 删 除 房 屋 信 息");
            System.out.println("\t\t\t4 修 改 房 屋 信 息");
            System.out.println("\t\t\t5 房 屋 列 表");
            System.out.println("\t\t\t6 退      出");
            System.out.print("请输入你的选择(1-6): ");
            // 用于接收用户输入
            char key = Utility.readMenuSelection();
            switch (key) {
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    updateHouse();
                    break;
                case '5':
                    showHouseList();
                    break;
                case '6':
                    exit();
                    break;
            }
        } while (loop);
    }
}
