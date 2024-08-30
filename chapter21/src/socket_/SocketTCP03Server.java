package socket_;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCP03Server {
    public static void main(String[] args) throws IOException {
        // 1. 创建监听套接字、监听9999端口
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器端监听9999端口，等待连接...");
        // 2. 没有连接，阻塞，建立连接，返回一个连接socket
        Socket socket = serverSocket.accept();
        System.out.println("服务器端 socket=" + socket.getClass());

        // 3. 读取客户端写入到数据通道的数据，没有就阻塞
        InputStream is = socket.getInputStream();
        // 4. IO读取 字符流方式
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        System.out.println(br.readLine());

        // 5. IO写入
        OutputStream os = socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write("hello, client 字符流");
        bw.flush();
        socket.shutdownOutput();

        // 5. 必须关闭流和Socket
        bw.close();
        br.close();
        socket.close();
        serverSocket.close();
        System.out.println("服务器退出...");
    }
}
