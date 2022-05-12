package Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient {
    private String host = "localhost";// 默认连接到本机
    private int port = 8189;// 默认连接到端口8189
    private String dataSen = "64 03 00 00 00 0A C5 CD";

    public TCPClient(){}

    public TCPClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void chat() {//chat方法
        try {
            // 连接到服务器
            Socket socket = new Socket(host, port);//创建Socket类对象

            try {

                DataInputStream in = new DataInputStream(socket.getInputStream());      // 读取服务器端传过来信息的DataInputStream
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());  // 向服务器端发送信息的DataOutputStream

                while (true) {
                    System.out.println("客户端：" + dataSen);//输出键盘输出内容提示 ，也就是客户端向服务器端发送的消息
                    // 把从控制台得到的信息传送给服务器
                    out.writeUTF("客户端：" + dataSen);//将客户端的信息传递给服务器
                    String accpet = in.readUTF();// 读取来自服务器的信息
                    System.out.println(accpet);//输出来自服务器的信息
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                socket.close();//关闭Socket监听
            }
        } catch (IOException e) {//捕获异常
            e.printStackTrace();
        }
    }

}
