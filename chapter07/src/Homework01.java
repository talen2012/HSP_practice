public class Homework01 {
    public static void main(String[] args) {
        double[] aa = {1.2, 3.0, -4.5, -9};
        A01 a01 = new A01();
        Double res = a01.maxDoubleArray(aa);
        if (res != null) {
            System.out.println("double数组aa的最大值是：" + a01.maxDoubleArray(aa));
        } else {
            System.out.println("输入的数组有误，数组不能为null或者{}");
        }
    }
}

class A01 {
    public Double maxDoubleArray(double[] arr) {
        // 先完成业务逻辑，再考虑代码健壮性
//        double max = arr[0]; // arr数据为空时下标越界异常，arr为null时空指针异常
        // 保证arr至少有1个元素
        if (arr != null && arr.length > 0) {
            double max = arr[0];
            for (double v : arr) {
                if (max < v) {
                    max = v;
                }
            }
            return max;
        } else {
            // 加入健壮性检查后，无法满足返回一个doule
            // 将返回类型修改为Double类
            return null;
        }
    }
}
