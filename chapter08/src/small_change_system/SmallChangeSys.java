package small_change_system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeSys {

    // 化繁为简
    // 1. 显示菜单，并可以选择菜单，给出对应提示
    // 2. 完成零钱通明细
    public static void main(String[] args) {
        // 定义相关变量
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        // 2. 完成零钱通明细
        // 把收益和消费信息保存到数组；使用对象；简单的话可以使用String拼接
        String details = "--------------零钱通明细--------------";
        // 3. 完成收益入账
        double money;
        double balance = 0;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // 4. 完成消费
        String note; // 消费说明
        // 5. 完成退出
        // 6. 补充退出确认
        // 7. 收益和消费金额验证

        do {
            System.out.println("\n==============零钱通菜单==============");
            System.out.println("\t\t\t1 零钱通明细");
            System.out.println("\t\t\t2 收益入账");
            System.out.println("\t\t\t3 消费");
            System.out.println("\t\t\t4 退出");
            System.out.print("请选择1-4：");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("1 零钱通明细");
                    System.out.println(details);
                    break;
                case 2:
                    System.out.println("2 收益入账");
                    System.out.print("收益入账金额：");
                    money = scanner.nextDouble();
                    // 金额校验
                    // 过关斩将校验法，先判断是否不满足条件，不满足就直接不进行了
                    // 好处是，不满足的条件可以灵活增加
                    if (money <= 0) {
                        System.out.println("输入的消费金额应该大于0");
                        break;
                    }
                    balance += money;
                    date = new Date(); // 获取当前日期和时间
                    details += "\n收益入账\t+" + money + "\t" + sdf.format(date) + "\t" + balance;
                    break;
                case 3:
                    System.out.println("3 消费");
                    System.out.print("消费金额：");
                    money = scanner.nextDouble();
                    if (money <= 0 || money > balance) {
                        System.out.println("消费金额应该在0-" + balance);
                        break;
                    }
                    System.out.print("消费说明：");
                    note = scanner.next();
                    balance -= money;
                    date = new Date();
                    details += "\n" + note + "\t-" + money + "\t" + sdf.format(date) + "\t" + balance;
                    break;
                case 4:
                    System.out.println("4 推出");
                    // 提示用户“你确定要退出吗，y/n?”，输入不是y/n则循环询问
                    // 建议一段代码完成一个功能，在循环里就判断输入的是不是y/n
                    String  answer = "";
                    while (true) {
                        System.out.println("你确定要退出吗，y/n?");
                        answer = scanner.next();
                        if ("y".equals(answer) || "n".equals(answer)) {
                            break;
                        }
                    }
                    if ("y".equals(answer)) {
                        loop = false;
                    }
                    break;
                default:
                    System.out.println("你的输入有误，请输入1-4");
            }

        } while(loop);

        System.out.println("===========退出了零钱通项目===========");
    }

    void showMenu() {
        System.out.println("==============零钱通菜单==============");
        System.out.println("\t\t\t1 零钱通明细");
        System.out.println("\t\t\t2 收益入账");
        System.out.println("\t\t\t3 消费");
        System.out.println("\t\t\t4 退出");
    }


}


//class SmallChange {
//    private double balance;
//
//    public void showMenu() {
//        System.out.println("--------------零钱通菜单--------------");
//        System.out.println("           1 零钱通明细");
//        System.out.println("           2 收益入账");
//        System.out.println("           3 消费");
//        System.out.println("           4 退出");
//    }
//}