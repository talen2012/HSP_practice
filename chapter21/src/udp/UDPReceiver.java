package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPReceiver {
    public static void main(String[] args) throws IOException {
        // 1. 创建一个DatagramSocket, 从9999端口接收数据
        DatagramSocket socket = new DatagramSocket(9999);
        // 2. 创建一个byte数组，用于构建DatagramPacket对象
        byte[] buf = new byte[1024];
        DatagramPacket packetRev = new DatagramPacket(buf, buf.length);// 用于接收，不用指定对端ip和端口
        // 3. 调用receive方法，填充DatagramPacket
        System.out.println("接收端准备接收数据...");
        socket.receive(packetRev);

        // 4. 拆包，取出数据并显示
        int length = packetRev.getLength(); // 实际收到的字节数
        byte[] dataRev = packetRev.getData(); // 完整的buf数组，包含尾部的0
        String s = new String(dataRev, 0, length);
        System.out.println(s);

        System.out.println("准备回复发送端...");
        byte[] dataSnd = "好的，明天见~".getBytes();
        DatagramPacket packetSnd = new DatagramPacket(dataSnd, dataSnd.length,
                InetAddress.getByName("10.69.205.186"), 9998);
        socket.send(packetSnd);
        System.out.println("回复成功！");

        // 5. 关闭资源
        socket.close();

    }
}
