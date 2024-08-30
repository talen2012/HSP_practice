package socket_;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTCP01Client {
    public static void main(String[] args) throws IOException {
        // 1. 获取套接字，连接服务器端(ip, 端口)
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket=" + socket.getClass());
        // 2. 得到和Socket关联的输出流对象
        OutputStream os = socket.getOutputStream();
        // 3. IO写入
        os.write("hello, server".getBytes());
        // 4. 关闭流和Socket
        os.close();
        socket.close();
        System.out.println("客户端退出...");
    }
}
