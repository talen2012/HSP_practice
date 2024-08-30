package upload;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPFileUploadCient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        // 1. 创建一个BufferedInputStream，读取"chapter21/src/mg.png"图片到buf数组中
        BufferedInputStream imgBis = new BufferedInputStream(new FileInputStream("chapter21/src/mg.png"));
        //    从图片获取byte数组
        byte[] imgByteArray = StreamUtils.streamToByteArray(imgBis);
        imgBis.close();
        // 2. 上传图片
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(imgByteArray);
        socket.shutdownOutput(); // 设置结束标记
        System.out.println("客户端上传图片完毕！");

        // 获取与socket关联的InputStream
        InputStream is = socket.getInputStream();
        String ack = StreamUtils.streamToString(is);
        if (ack.contains("已收到图片")) {
            System.out.println("收到服务器已收到图片的通知");
        }
        // 关闭流和socket
        is.close();
        bos.close();
        socket.close();
    }
}
