package udp;

import java.io.IOException;
import java.net.*;

public class UDPSender {
    public static void main(String[] args) throws IOException {
        // 1. 创建一个DatagramSocket, 从9998接收数据
        DatagramSocket socket = new DatagramSocket(9998);
        // 2. 将要发送的byte数组，传给DatagramPackt
        //    DatagramPacket, 指定对端的ip和端口，用于发送数据
        byte[] dataSnd = "hello, 明天吃火锅~".getBytes();
        DatagramPacket packetSnd = new DatagramPacket(dataSnd, dataSnd.length,
                InetAddress.getByName("10.69.205.186"), 9999);

        // 3. 调用send方法完成发送
        socket.send(packetSnd);
        System.out.println("发送端已发送数据！");

        // 接收接收端的回复
        byte[] buf = new byte[1024];
        DatagramPacket packetRev = new DatagramPacket(buf, buf.length);
        System.out.println("准备接收接收端的回复...");
        socket.receive(packetRev);

        int lenRev = packetRev.getLength();
        byte[] dataRev = packetRev.getData();
        String s = new String(dataRev, 0, lenRev);
        System.out.println(s);

        // 4. 关闭资源
        socket.close();

    }
}
