package Util;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class TCPClient {
    //private String host = "localhost";// 默认连接到本机
    //private int port = 8189;// 默认连接到端口8189
    private static String dataSend = "01 03 00 00 00 0A C5 CD";


    public static void testIP() throws IOException {

        InetAddress ip = InetAddress.getLocalHost();
        System.out.println(ip);
        System.out.println(ip.getHostAddress());
    }

    public static void testPort(){
        InetSocketAddress socketAddr = new InetSocketAddress("192.168.1.2",9527);
        System.out.println(socketAddr.getHostName());
        System.out.println(socketAddr.getPort());
        System.out.println(socketAddr.getHostString());
    }

    public static void testClient() {
        try {
            SocketAddress socketAddress = new InetSocketAddress("192.168.1.101", 9527);
            Socket s = new Socket();
//            s.bind(socketAddress);
            s.connect(socketAddress,10*1000);
            //Socket s = new Socket("192.168.1.101", 9527);

            try{
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                int count = 5;
                while (count>0) {
                    String data = "client with time:" + System.currentTimeMillis();
                    //System.out.println(dataSend);
                    dos.writeUTF(dataSend);
                    String accept = dis.readUTF();
                    System.out.println(accept);
                    count--;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                s.close();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
