public class HanoiTower {
    public static void main(String[] args) {
        T3 t3 = new T3();
        t3.move(3, 'A', 'B', 'C');
    }
}

class T3 {
    // num是a柱上的圆盘个数，a, b, c分别是三个柱子
    // 要求将a柱上的所有圆盘移动到c柱上，过程中大盘不能放到小盘上面
    public void move(int num, char a, char b, char c) {
        if (num == 1) { // 递归的跳出条件：num为1，只有一个圆盘，直接移动
            System.out.println(a + "->" + c);
        } else {
            // 有多个盘，看作两个，最下面一个和上面的所有盘(num - 1)
            // 1.先移动a柱上面所有盘到b柱，借助c柱
            move(num - 1, a, c, b);
            // 2.移动a最下面一个盘到c柱
            System.out.println(a + "->" + c);
            // 3.移动b柱所有盘到C盘，借助a盘
            move(num - 1, b, a, c);
        }
    }
}
