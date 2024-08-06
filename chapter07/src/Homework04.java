public class Homework04 {
    public static void main(String[] args) {
        A03 a03 = new A03();
        int[] arr = {100, 20, 30};
        a03.printArr(arr);
        int[] arrNew = a03.copyArr(arr);
        a03.printArr(arrNew);
    }
}

//class A03 {
//    public int[] copyArr(int[] arr) {
//        int[] arrNew = new int[arr.length];
//        for (int i = 0; i < arr.length; i++) {
//            arrNew[i] = arr[i];
//        }
//        return arrNew;
//    }
//
//    public void printArr(int[] arr) {
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + "\t");
//        }
//        System.out.println();
//    }
//}

// 添加代码健壮性，考虑arr为空
class A03 {
    public int[] copyArr(int[] arr) {
        if (arr != null) {
            int[] arrNew = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                arrNew[i] = arr[i];
            }
            return arrNew;
        } else {
            return null;
        }
    }

    public void printArr(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + "\t");
            }
            System.out.println();
        } else {
            System.out.println("数组指向null");
        }
    }
}