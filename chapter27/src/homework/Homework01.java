package homework;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/3
 * @time 20:47
 * 验证邮箱账号
 */
public class Homework01 {
    public static void main(String[] args) {
        String content = "sheizhitianliangyi@sohu.com";

        String regStr = "^[\\w-]+@([a-zA-Z]+\\.)+[a-zA-Z]+$";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);

        System.out.println(Pattern.matches(regStr, content)); // 使用matches方法本身就指的是整体匹配，不加前后的定位符也可以
        while (matcher.find()) {
            System.out.println("整体匹配：" + matcher.group(0));
            System.out.println("分组1：" + matcher.group(1));
        }
    }
}

