package socket_;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketTCP03Client {
    public static void main(String[] args) throws IOException {
        // 1. 获取套接字，连接服务器端(ip, 端口)
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket=" + socket.getClass());
        // 2. 得到和Socket关联的输出流对象
        OutputStream os = socket.getOutputStream();
        // 3. IO写入, 字符流
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write("hello, server 字符流");
        bw.newLine(); // 设置结束标记
        bw.flush(); // 必须手动调用刷新，才能写入数据通道
        // 4. 得到和socket关联的输入流对象，并读取
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        System.out.println(br.readLine());

        // 5. 关闭流和Socket
        br.close();
        bw.close();
        socket.close();
        System.out.println("客户端退出...");
    }
}
