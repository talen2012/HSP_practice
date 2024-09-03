package homework;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 田磊
 * @version 1.0
 * @date 2024/9/3
 * @time 21:31
 * 对一个URL进行解析
 */
public class Homework03 {
    public static void main(String[] args) {
        String URL = "https://www.sohu.com:8080/abc/xxx/yyy/index.htm";
        // 要求得到协议、域名、端口、文件名
        String regStr = "^(?<protocol>https?)://(?<domain>([a-zA-Z]+\\.)+[a-z]+):(?<port>\\d{1,5})(/[\\w-]+)*/(?<file>[\\w-]+\\.[\\w-]+)$";
        // 正则表达式中的(/(?<file>[\\w-]+\\.[\\w-]+)$部分确实会影响/[\\w-]+)*匹配结果。由于*是贪婪匹配，它会尝试匹配尽可能多的字符，
        // 但在遇到/(?<file>[\\w-]+\\.[\\w-]+)$时，它会回溯以确保整个表达式能够匹配。
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(URL);

        if (matcher.matches()) {
            System.out.println("协议：" + matcher.group("protocol"));
            System.out.println("域名：" + matcher.group("domain"));
            System.out.println("端口：" + matcher.group("port"));
            System.out.println("文件：" + matcher.group("file"));
        }
    }
}
