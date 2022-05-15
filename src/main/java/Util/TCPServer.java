package Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class TCPServer {
    private int port;
    private String sendData = "01 03 00 00 00 0A C5 CD";

    public TCPServer(int port){
        this.port = port;
    }

    //创建service方法
    public void service() {
        try {
            System.out.println("TCP server starting...");
            // 建立服务器连接
            ServerSocket server = new ServerSocket(port);//创建  ServerSocket类
            System.out.println("TCP server create finish.Now waiting for client connection...");
            //SocketAddress socketAddress = new InetSocketAddress("192.168.1.101", port);
            //server.bind(socketAddress);
            Socket socket = server.accept();// 等待客户连接
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());      // 读取客户端传过来信息的DataInputStream
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());  // 向客户端发送信息的DataOutputStream

                while (true) {
                    String accept = in.readUTF();// 读取来自客户端的信息
                    System.out.println(accept);//输出来自客户端的信息

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

    public void connect() {
        try{
            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress("192.168.1.101", port);
            socket.connect(socketAddress, 10*1000);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("服务器：" + sendData);//输出提示信息
            out.writeUTF("服务器：" + sendData);//把服务器端的输入发给客户端

            String accept = in.readUTF();// 读取来自客户端的信息
            System.out.println(accept);//输出来自客户端的信息
            Thread.sleep(10*1000);
            socket.close();//关闭连接
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }

}
