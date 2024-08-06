public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {24, 69,80,57, 13};
        int temp = 0;
        for (int i = 0; i < arr.length -1; i++) { // 比较N-1轮，这里5个元素，比较4轮，最后一轮排序第一个和第二个元素
            for (int j = 0; j < arr.length -1 - i; j++) { // 每一轮将未排序元素中的最大值放大最后
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
