package small_change_system;

public class SmallChangeSysApp {
    public static void main(String[] args) {
        SmallChangeSysOOP scs = new SmallChangeSysOOP();
        do {
            scs.choice = scs.showMenuAndGetChoice();
            switch (scs.choice) {
                case 1:
                    scs.showDetails();
                    break;
                case 2:
                    scs.income();
                    break;
                case 3:
                    scs.consume();
                    break;
                case 4:
                    scs.exit();
                    break;
                default:
                    System.out.println("输入有误，请重新输入");
            }
        } while (scs.loop);
        System.out.println("===========退出了零钱通项目===========");
    }
}
