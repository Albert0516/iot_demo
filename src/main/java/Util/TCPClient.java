package Util;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {
    //private String host = "localhost";// 默认连接到本机
    //private int port = 8189;// 默认连接到端口8189
    private static String dataSend = "64 03 00 00 00 0A C5 CD";


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
            Socket s = new Socket("192.168.4.1", 9527);
            try{
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                int count = 30;
                while (count>0) {
                    String data = "client with time:" + System.currentTimeMillis();
                    //System.out.println(dataSend);
                    dos.writeUTF(dataSend);
                    String accept = dis.readUTF();
                    System.out.println(accept);
                    count--;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }finally {
                s.close();
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
