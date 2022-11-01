package com.yts.system.net;

import com.yts.system.constant.BookConstant;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPClient {

    static TCPClient connection = new TCPClient();

    public static TCPClient getConnection(){
        return connection;
    }

    private Socket tcpConnection;
    private InputStream in = null;
    private OutputStream out = null;
    private InputStreamReader isr = null;
    private BufferedReader br = null;

    private TCPClient(){
        try {
            tcpConnection = new Socket("127.0.0.1", 10086);
            System.out.println("成功与服务器建立连接-------");
            in = this.tcpConnection.getInputStream();
            out = this.tcpConnection.getOutputStream();
            isr = new InputStreamReader(in);
            br = new BufferedReader(isr);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String request(String request){

        try {
            out.write((request + "\n").getBytes(StandardCharsets.UTF_8));
            System.out.println("request: " + request);
            String response = br.readLine();
            System.out.println("response: " + response);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BookConstant.FAILURE;
    }

    public void close(){
        try {
            //向服务器发送断开请求
            request(BookConstant.DISCONNECT);
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
            if (tcpConnection != null){
                tcpConnection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
