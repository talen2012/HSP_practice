package homework;

import upload.StreamUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Homework03Server {
    public static void main(String[] args) throws IOException {
        // 1. 创建监听socket
        ServerSocket serverSocket = new ServerSocket(8888);
        // 2. 获取连接socket
        System.out.println("服务器正在监听8888端口，等待连接...");
        Socket socket = serverSocket.accept();
        // 3. 接收用户消息
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String downloadFileName = br.readLine();

        System.out.println("用户希望下载的文件名=" + downloadFileName);
        // 4. 根据用户消息，发送对应音乐文件
        String musicFilePath = "";
        if ("高山流水.mp3".equals(downloadFileName)) {
            musicFilePath = "chapter21/src/高山流水.mp3";
        } else {
            System.out.println("没有用户想要的音乐，发送“无名.mp3”");
            musicFilePath = "chapter21/src/无名.mp3";
        }
        // // 从文件获取bytes
        BufferedInputStream bis =
                new BufferedInputStream(new FileInputStream(musicFilePath));
        byte[] musicBytes = StreamUtils.streamToByteArray(bis);
        bis.close();
        // // 通过socket发送
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(musicBytes);
        socket.shutdownOutput(); // 设置结束标记
        System.out.println("发送" + musicFilePath + "成功！");

        // 5. 关闭资源
        bos.close();
        br.close();
        socket.close();
        serverSocket.close();
    }
}
