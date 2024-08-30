package homework;

import upload.StreamUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Homework03Client {
    public static void main(String[] args) throws IOException {
        // 1. 创建Socket
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);

        // 从键盘读入要从服务器获取的音乐文件名
        Scanner myScanner = new Scanner(System.in);
        System.out.println("请输入要下载的音乐文件名：");
        String musicFile = myScanner.next();

        // 2. 向服务器发送要下载的音乐名
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write(musicFile);
        bw.newLine();
        bw.flush();
        System.out.println("已向服务器发送请求！");

        // 3. 等待并接收服务器发送的音乐文件
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] musicBytes = StreamUtils.streamToByteArray(bis);
        //     写入到本地文件
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("chapter21/src/musicReceived.mp3"));
        bos.write(musicBytes);

        // 4. 关闭资源
        bos.close();
        bis.close();
        bw.close();
        socket.close();
    }
}
