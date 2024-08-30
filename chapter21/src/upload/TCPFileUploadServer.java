package upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPFileUploadServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器监听9999端口，等待连接...");
        Socket socket = serverSocket.accept();
        System.out.println("建立连接...");
        // 获取socket关联的输入流, 从流获得图片byte数组
        InputStream is = socket.getInputStream();
        byte[] imgByteArray = StreamUtils.streamToByteArray(is);
        // 创建BufferedOutputStream, 用于保存收到的图片
        BufferedOutputStream imgBos = new BufferedOutputStream(new FileOutputStream("chapter21/src/mg_copy.png"));
        imgBos.write(imgByteArray);
        imgBos.close();
        System.out.println("服务端接收图片并保存成功！");

        // 发送"已收到图片"通知给服务器
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write("已收到图片");
        bw.flush();
        socket.shutdownOutput();
        // 关闭流和socket
        bw.close();
        is.close();
        socket.close();
        serverSocket.close();
    }
}
