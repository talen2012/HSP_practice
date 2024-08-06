public class Homework06 {
    public static void main(String[] args) {
        Calc calc1 = new Calc(6, 0);
        System.out.println(calc1.op1 + "+" + calc1.op2 + "=" + calc1.add());
        System.out.println(calc1.op1 + "-" + calc1.op2 + "=" + calc1.sub());
        System.out.println(calc1.op1 + "*" + calc1.op2 + "=" + calc1.mul());
        Integer res = calc1.div();
        if (res != null) {
            System.out.println(calc1.op1 + "/" + calc1.op2 + "=" + calc1.div());
        }
    }
}

// 整数的加减乘除计算器
class Calc {
    int op1;
    int op2;

    public Calc(int num1, int num2) {
        this.op1 = num1;
        this.op2 = num2;
    }

    public int add() {
        return op1 + op2;
    }

    public int sub() {
        return op1 - op2;
    }

    public int mul() {
        return op1 * op2;
    }

    public Integer div() {
        if (op2 != 0) {
            return op1 / op2;
        } else {
            System.out.println("除数不能为0！");
            return null;
        }
    }
}

