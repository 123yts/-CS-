package com.yts.system.util;

import com.yts.system.constant.BookConstant;
import com.yts.system.model.Book;

public class Protocol {


    public static String addBookRequest(Book book){
        StringBuffer request = new StringBuffer();
        request.append(BookConstant.ADD).append("&").
                append(book.getNumber()).append(";").
                append(book.getName()).append(";").
                append(book.getAuthor()).append(";").
                append(book.getPublisher()).append(";").
                append(book.getPrice()).append(";").
                append(book.isBorrowed() ? "1" : "0");
        return request.toString();
    }

    public static String updateBookRequest(Book book){
        StringBuffer request = new StringBuffer();
        request.append(BookConstant.UPDATE).append("&").
                append(book.getNumber()).append(";").
                append(book.getName()).append(";").
                append(book.getAuthor()).append(";").
                append(book.getPublisher()).append(";").
                append(book.getPrice()).append(";").
                append(book.isBorrowed() ? "1" : "0");
        return request.toString();
    }

    public static String deleteBookRequest(int[] numbers){
        StringBuffer request = new StringBuffer();
        request.append(BookConstant.DELETE).append("&");
        for (int i = 0; i < numbers.length; i++) {
            request.append("" + numbers[i]);
            if (i < numbers.length - 1) request.append("|");
        }
        return request.toString();
    }

    public static String queryBookByNameRequest(String bookName, int pageNow, int pageSize){
        StringBuffer request = new StringBuffer();
        request.append(BookConstant.QUERY).append("&");
        request.append(bookName).append("|").
                append(pageNow).append("|").
                append(pageSize);
        return request.toString();
    }

    public static String getTotalCountByNameRequest(String name){
        StringBuffer request = new StringBuffer();
        request.append(BookConstant.QUERY_TOTAL_COUNT).append("&");
        request.append(name);
        return request.toString();
    }

    public static String queryBookByNumberRequest(int number){
        StringBuffer request = new StringBuffer();
        request.append(BookConstant.QUERY_BY_NUMBER).append("&");
        request.append(number);
        return request.toString();
    }
}
