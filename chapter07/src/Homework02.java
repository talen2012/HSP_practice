public class Homework02 {
    public static void main(String[] args) {
        String[] strs = {"tom", "mary", "Jack"};
        String str = "tom";
        A02 a02 = new A02();
        int index = a02.find(strs, str);
        if (index == -1) {
            System.out.println("没有元素" + str);
        } else {
            System.out.println("找到元素" + str + "，索引是：" + index);
        }
    }
}

//class A02 {
//    // 查找字符串数组中某个元素的索引，没找到返回-1
//    public int find(String[] strs, String str) {
//        for(int i = 0; i < strs.length; i++) {
//            if (strs[i].equals(str)) {
//                return i;
//            }
//        }
//        return -1;
//    }
//}

// 补充代码健壮性，考虑strs为空
class A02 {
    // 查找字符串数组中某个元素的索引，没找到返回-1
    public int find(String[] strs, String str) {
        if (strs != null) {
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].equals(str)) {
                    return i;
                }
            }
        }
        return -1;
    }
}