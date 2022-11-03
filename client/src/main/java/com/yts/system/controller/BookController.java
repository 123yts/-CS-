package com.yts.system.controller;

import com.yts.system.constant.BookConstant;
import com.yts.system.model.Book;
import com.yts.system.net.TCPClient;
import com.yts.system.util.Parser;
import com.yts.system.util.Protocol;

import java.util.Vector;

public class BookController {

    TCPClient tcpConnection = TCPClient.getConnection();

    public Book queryBookByNumber(int number){
        String response = tcpConnection.request(Protocol.queryBookByNumberRequest(number));
        if (BookConstant.FAILURE.equals(response)) return null;
        return Parser.getBook(response);
    }

    public String updateBookByNumber(Book book){
        String response = tcpConnection.request(Protocol.updateBookRequest(book));
        return response;
    }


    public String deleteBookByNumbers(int[] numbers){
        String response = tcpConnection.request(Protocol.deleteBookRequest(numbers));
        return response;
    }

    public String addBook(Book book){
        String response = tcpConnection.request(Protocol.addBookRequest(book));
        return response;
    }

    public String getTotalCountByName(String name){
        String response = tcpConnection.request(Protocol.getTotalCountByNameRequest(name));
        return response;
    }

    public Vector<Vector<Object>> queryBookListByName(String name, int pageNow, int pageSize){
        String response = tcpConnection.request(Protocol.queryBookByNameRequest("#", pageNow, pageSize));
        Vector<Vector<Object>> data = Parser.getTableData(response);
        return data;
    }


}
