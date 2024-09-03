package homework;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/3
 * @time 21:08
 * 验证是否为整数或小数
 */
public class Homework02 {
    public static void main(String[] args) {
        String content = "-454.33";
        // String regStr = "^[-+]?\\d+\\.?\\d*$";
        // 上面这种写法是错的，因为小数点一旦出现就必须跟着数字

        // String regStr = "^[-+]?\\d+(\\.\\d+)?$";
        // 这么写也不完善，因为"-00124.33"也会验证通过
        // 因此要么以1-9开头，如果以0开头，则只能有一个，而且其后紧跟一个.
        String regStr = "^[-+]?([1-9]\\d*|0)(\\.\\d+)?$";

        System.out.println(content.matches(regStr));
    }
}
