package Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer {
    private int port = 8189;
    private String sendData = "64 03 00 00 00 0A C5 CD";

    public TCPServer(){}

    public TCPServer(int port){
        this.port = port;
    }

    //创建service方法
    public void service() {
        try {
            System.out.println("TCP server starting...");
            // 建立服务器连接
            ServerSocket server = new ServerSocket(port);//创建  ServerSocket类
            Socket socket = server.accept();// 等待客户连接
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());      // 读取客户端传过来信息的DataInputStream
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());  // 向客户端发送信息的DataOutputStream

                while (true) {
                    String accpet = in.readUTF();// 读取来自客户端的信息
                    System.out.println(accpet);//输出来自客户端的信息

                    System.out.println("服务器：" + sendData);//输出提示信息
                    out.writeUTF("服务器：" + sendData);//把服务器端的输入发给客户端
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                socket.close();//关闭连接
                server.close();//关闭
            }
        } catch (IOException e) {//捕获异常
            e.printStackTrace();
        }
    }

}
