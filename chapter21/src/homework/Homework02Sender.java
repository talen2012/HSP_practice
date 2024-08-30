package homework;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Homework02Sender {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9998);

        // 从键盘接收问题
        Scanner myScanner = new Scanner(System.in);
        System.out.println("请输入你的问题：");
        String question = myScanner.next();
        // 发送问题给接收端
        byte[] data = question.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("10.69.205.186"), 9999);
        socket.send(packet);

        // 接收回复
        byte[] buf = new byte[1024];
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        int len = 0;
        len = packet.getLength();
        data = packet.getData();
        String answer = new String(data, 0, len);
        System.out.println(answer);

        // 关闭资源
        socket.close();
    }
}
