package com.yts.system.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private TCPServer(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(10086);
            System.out.println("服务器启动-------");
            while (true){
                Socket tcpConnection = serverSocket.accept();
                new Thread(new BookManageThread(tcpConnection)).start();
                System.out.println("收到客户端 " + tcpConnection.getInetAddress() + ":" + tcpConnection.getPort() + " 的连接");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (serverSocket != null){
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new TCPServer();
    }
}
