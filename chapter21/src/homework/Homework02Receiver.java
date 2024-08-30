package homework;

import java.io.IOException;
import java.net.*;

public class Homework02Receiver {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9999);

        // 接收发送端的问题
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        System.out.println("接收端等待接收问题...");
        socket.receive(packet);
        int len = packet.getLength();
        byte[] data = packet.getData();
        String question = new String(data, 0, len);

        String answer;
        if ("四大名著是什么？".equals(question)) {
            answer = "四大名著是《红楼梦》、《西游记》、《三国演义》和《水浒传》";
        } else {
            answer = "what?";
        }

        // 回复发送端
        data = answer.getBytes();
        packet = new DatagramPacket(data, data.length, InetAddress.getByName("10.69.205.186"), 9998);
        socket.send(packet);

        // 关闭资源
        socket.close();
    }
}
