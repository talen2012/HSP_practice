public class YangHui {
    // 输出一个十行的杨辉三角形
    public static void main(String[] args) {
        int[][] yangHui = new int[10][]; // 开辟10个一维数组的地址的空间，一维数组的空间未开辟
        for (int i = 0; i < yangHui.length; i++) {
            // 给每个一维数组开辟空间
            yangHui[i] = new int[i+1];
            // 给每个一维数组赋值
            for (int j = 0; j < yangHui[i].length; j++) {
                if (j == 0 || j == yangHui[i].length - 1) {
                    yangHui[i][j] = 1;
                } else { // 中间的元素，注意这里不用判断是否是从第三行开始，因为前两行都在if中执行，不会进入else
                    yangHui[i][j] = yangHui[i-1][j-1] + yangHui[i-1][j];
                }
            }
        }

        for (int i = 0; i < yangHui.length; i++) {
            for (int j = 0; j < yangHui[i].length; j++) {
                System.out.print(yangHui[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
