package com.yts.system.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.yts.system.constant.BookConstant;
import com.yts.system.model.Book;

import java.util.List;

public class Protocol {

    public static String  retBookList(List<Book> bookList){

        return JSONObject.toJSONString(bookList);
    }

    public static String retBook(Book book){
    if (book == null){
        return BookConstant.FAILURE;
    }

    return JSON.toJSONString(book);

    }

}
