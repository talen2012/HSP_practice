package homework;

import upload.StreamUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Homework01Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        int i = 0;
        while (i < 3) { // 问3次
            // 从键盘读取用户的问题
            Scanner myScanner = new Scanner(System.in);
            System.out.print("请输入你的问题：");
            String question = myScanner.next();

            //  发送给服务器
            bw.write(question);
            bw.newLine();
            bw.flush(); // 一定要刷新，才能写入

            // 接收服务器反馈
            String answer = br.readLine();
            System.out.println("服务器回答：" + answer);
            i++;
        }

        // 关闭资源
        br.close();
        bw.close();
        socket.close();
    }
}
