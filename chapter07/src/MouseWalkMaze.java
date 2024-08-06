public class MouseWalkMaze {
    public static void main(String[] args) {
        // 思路
        // 1. 二维数组表示迷宫，元素0表示可以走，1表示障碍物
        int [][] map = new int[8][7];
        // 2. 最上面一行和最下面一行全部设置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // 3. 最左一行和最右一行全部设为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[2][2] = 1;
        // 输出当前的地图
        System.out.println("=====当前地图情况=====");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        T2 t2 = new T2();
        t2.findWay(map, 1, 1);
        System.out.println("=====探索后地图情况=====");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }
}

class T2 {
    // 使用递归回溯的方式解决老鼠走迷宫
    // 1. findWay方法, 返回初始位置(i,j)是否可到达目的地(6,5)
    // 初始位置(i,j)可到达目的地，则其四周至少有一个位置也可以到达目的地
    // 递进进行，直到到达目的地(6,5)时，开始返回
    // 地图中表示：0-没有障碍物 1-有障碍物 2-已走过可走通 3-已走过是死路
    public boolean findWay(int[][] map, int i, int j) {
        // 目的地是(6,5), 走到这里就认为任务完成
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) { // 当前位置未走过且不是障碍物
                // 走到i, j这个位置上，先假定为2
                map[i][j] = 2;
                // 按照下-右-左-上的搜索策略探索四周
                // 递归进行
                if (findWay(map, i + 1, j)) {
                    return true;
                } else if (findWay(map, i, j + 1)) {
                    return true;
                } else if (findWay(map, i - 1, j)) {
                    return true;
                } else if (findWay(map, i, j - 1)) {
                    return true;
                } else { // 四周没有未走过且不是障碍物的路，是死路
                    // 这里就没有递归了，直接返回了false
                    // 意思就是四周都是障碍物或已探索过，要回溯到上一个位置
                    map[i][j] = 3;
                    return false;
                }
            } else { // 当前位置为障碍物或已走过，直接返回，不递归
                return false;
            }
        }
    }
}
