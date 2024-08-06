public class Homework04 {
    /**
     * 已有一个升序数组，要求插入一个元素后仍然是升序
     */
    public static void main(String[] args) {
        int[] arr = {10, 12, 45, 90};
        int insertNum = 23;
        int index = -1; // index就是要插入的地址

        // 1. 定位
        // 遍历原数组，碰到insertNum <= arr[i], 活命i就是要插入的位置
        // 使用index记录i
        // 如果遍历完都没有insertNum <= arr[i], 则index没有被修改，说明arr.length是要插入的位置
        for (int i = 0; i < arr.length; i++) {
            if (insertNum <= arr[i]) {
                index = i;
                break;
            }
        }
        // 判断index值
        if (index == -1) {
            index = arr.length;
        }

        // 2. 扩容
        int[] arrNew = new int[arr.length + 1];
        // 将arr的元素拷贝到arrNew, 并且要跳过index
        for (int i = 0, j = 0; i < arrNew.length; i++) {
            if (i != index) { // 说明可以把arr的元素赋值给arrNew
                arrNew[i] = arr[j];
                j++;
            } else { // i这个位置就是要插入的位置
                arrNew[i] = insertNum;
            }
        }
        // arr指向新数组，原来的数组成为垃圾被JVM回收
        arr = arrNew;
        for (int m: arr) {
            System.out.print(m + "\t");
        }
        System.out.println();
    }
}
