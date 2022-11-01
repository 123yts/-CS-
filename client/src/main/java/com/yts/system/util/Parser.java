package com.yts.system.util;

import com.yts.system.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Parser {

    public static String[] getActionAndData(String request){
        // & 分割请求类型和数据
        String[] strings = request.split("&");
        return strings;
    }

    // | 分割每一本book
    public static List<Book> getBookList(String data){
        String[] bookStrs = data.split("\\|");
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < bookStrs.length; i++) {
            bookList.add(getBook(bookStrs[i]));
        }
        return bookList;
    }

    // ; 分割每一本书的每一个字段
    public static Book getBook(String bookStr){
        String[] strs = bookStr.split(";");
        return new Book(Integer.parseInt(strs[0]), strs[1], strs[2], strs[3],
                Double.parseDouble(strs[4]), "1".equals(strs[5]));
    }

    public static int[] getBookNumbers(String numberStr){
        String[] strs = numberStr.split("\\|");
        int[] numbers = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            numbers[i] = Integer.parseInt(strs[i]);
        }
        return numbers;
    }

    public static String[] getBookQuery(String query){
        String[] strs = query.split("\\|");
        return strs;
    }

    public static Vector<Vector<Object>> getTableData(String respose){
        List<Book> bookList = getBookList(respose);
        Vector<Vector<Object>> data = new Vector<>();
        for (Book book : bookList) {
            Vector<Object> item = new Vector<>();
            item.addElement(book.getNumber());
            item.addElement(book.getName());
            item.addElement(book.getAuthor());
            item.addElement(book.getPublisher());
            item.addElement(book.getPrice());
            item.addElement(book.isBorrowed());
            data.addElement(item);
        }
        return data;
    }

}
