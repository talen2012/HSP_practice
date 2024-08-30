package upload;

import java.io.*;
import java.nio.Buffer;

public class StreamUtils {

    /**
     * 从一个输入流获取内容保存为一个字节数组后返回
     * @param is
     * @return
     */
    public static byte[] streamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); // 无参构造默认先初始化容量为32字节
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = is.read(buf)) != -1) {
            baos.write(buf, 0, len); // 内部
        }
        baos.close();
        return baos.toByteArray();
    }

    /**
     * 从一个输入流获取内容，并转换为字符串
     * @param is
     * @return
     * @throws IOException
     */
    public static String streamToString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder(); // 用来保存读到的字符串
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
        }
        br.close();
        return sb.toString();
    }
}
