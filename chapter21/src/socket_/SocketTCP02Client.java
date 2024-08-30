package socket_;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SocketTCP02Client {
    public static void main(String[] args) throws IOException {
        // 1. 获取套接字，连接服务器端(ip, 端口)
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket=" + socket.getClass());
        // 2. 得到和Socket关联的输出流对象
        OutputStream os = socket.getOutputStream();
        // 3. IO写入
        os.write("hello, server".getBytes());
        //    设置写入结束标记
        socket.shutdownOutput();
        // 4. 得到和socket关联的输入流对象，并读取
        InputStream is = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = is.read(buf)) != -1) {
            System.out.print(new String(buf, 0, readLen));
        }
        System.out.println();

        // 5. 关闭流和Socket
        is.close();
        os.close();
        socket.close();
        System.out.println("客户端退出...");
    }
}
