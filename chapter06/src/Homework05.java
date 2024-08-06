public class Homework05 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        // 随机生成10个整数[1-100]保存到数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 100) + 1;
        }

        // 倒叙打印并求平均值、求最大值和最大值的下标
        int sum = 0;
        int maxNum = arr[arr.length - 1];
        int maxIndex = arr.length - 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + "\t");
            sum += arr[i];
            if (maxNum < arr[i]) {
                maxNum = arr[i];
                maxIndex = i;
            }
        }
        System.out.println();
        System.out.println("平均值：" + (sum / arr.length));
        System.out.println("最大值：" + maxNum);
        System.out.println("最大值索引：" + maxIndex);
    }
}
