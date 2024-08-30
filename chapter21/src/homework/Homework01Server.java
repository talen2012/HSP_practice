package homework;

import upload.StreamUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Homework01Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器监听8888端口，等待连接...");
        Socket socket = serverSocket.accept();

        System.out.println("连接成功，等待接收客户端消息...");

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        int i = 0;
        while (i < 3) {
            // 读取客户端消息
            String question = br.readLine();
            String answer;
            if ("name".equals(question)) {
                answer = "我是田磊";
            } else if ("hobby".equals(question)) {
                answer = "编写java程序";
            } else {
                answer = "你在说啥子";
            }

            // 根据收到的消息回复
            bw.write(answer);
            bw.newLine();
            bw.flush();
            i++;
        }


        // 关闭资源
        bw.close();
        br.close();
        socket.close();
        serverSocket.close();
    }
}
