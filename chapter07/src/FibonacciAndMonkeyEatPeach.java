public class FibonacciAndMonkeyEatPeach {
    public static void main(String []args) {
        T t1 = new T();
        int n = 7;
        int res = t1.getFibonacci(n);
        if (res != -1) {
            System.out.println("第" + n +"个斐波那契数是：" + res);
        }
        int day = 8;
        int peachNum = t1.peach(day);
        if (peachNum != -1) {
            System.out.println("第" + day + "天有" + peachNum + "个桃子！");
        }
    }
}

class T {
    /**
     使用递归求斐波那契数列1,1,2,3,5,8,13...
     给出第n个斐波那契数
     思路分析
     1. 当n=1, 斐波那契数是1；
     2. 当n=2，斐波那契数是1；
     3. 当n>2, 斐波那契数是前两数之和
     */
    public int getFibonacci(int n) {
        if (n >= 1) {
            if (n == 1 || n == 2) {
                return 1;
            } else {
                return getFibonacci(n - 1) + getFibonacci(n - 2);
            }
        } else {
            System.out.println("要求输入的n>=1");
            return -1;
        }
    }

    /*
      已知猴子每天吃当天桃子的一半再多吃一个，第十天有1个桃子，求第day天的桃子数
      第十天：1
      第九天：(day10 + 1) * 2 = 4;
      第八天：(day9 + 1) * 2 = 10;
      ...
    */
    public int peach(int day) {
        if (day == 10) {
            return 1;
        } else if (day >= 1 && day <= 9) {
            return (peach(day + 1) + 1) * 2;
        } else {
            System.out.println("要求输入的天数在1-9之间");
            return -1;
        }
    }
}