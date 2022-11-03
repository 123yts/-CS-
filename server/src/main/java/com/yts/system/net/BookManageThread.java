package com.yts.system.net;

import com.yts.system.constant.BookConstant;
import com.yts.system.controller.BookController;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class BookManageThread implements Runnable{

    private Socket tcpConnection;
    private BookController bookController = new BookController();

    BookManageThread(Socket tcpConnection){
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            while (true){
                in = tcpConnection.getInputStream();
                out = tcpConnection.getOutputStream();
                isr = new InputStreamReader(in);
                br = new BufferedReader(isr);

                //客户端请求报文
                String request = br.readLine();
                System.out.println("request: " + request);

                //客户端是否要断开连接
                if (BookConstant.DISCONNECT.equals(request)){
                    break;
                }

                //响应报文
                String response = null;
                response = bookController.response(request);

                out.write((response + "\n").getBytes(StandardCharsets.UTF_8));
                System.out.println("response: " + response);
            }
            System.out.println("断开与客户端IP:" + tcpConnection.getInetAddress() + "，端口号："+ tcpConnection.getPort() + "的连接");

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null){
                    br.close();
                }
                if (isr != null){
                    isr.close();
                }
                if (in != null){
                    in.close();
                }
                if (out != null){
                    out.close();
                }
                if (this.tcpConnection != null){
                    tcpConnection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
