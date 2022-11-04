package com.yts.system.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.yts.system.model.Book;
import java.util.List;
import java.util.Vector;

public class Parser {

    public static Book getBook(String bookStr){
        return JSONObject.parseObject(bookStr, Book.class);
    }

    public static Vector<Vector<Object>> getTableData(String response){

        List<Book> bookList = JSON.parseArray(response, Book.class);
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
