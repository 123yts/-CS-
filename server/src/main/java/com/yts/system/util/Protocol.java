package com.yts.system.util;

import com.yts.system.constant.BookConstant;
import com.yts.system.model.Book;

import java.util.List;

public class Protocol {

    public static String  retBookList(List<Book> bookList){
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            str.append(book.getNumber()).append(";").
                    append(book.getName()).append(";")
                    .append(book.getAuthor()).append(";").
                    append(book.getPublisher()).append(";").
                    append(book.getPrice()).append(";")
                    .append(book.isBorrowed() ? "1":"0");
            if (i < bookList.size()-1) str.append("|");
        }
        return str.toString();
    }

    public static String retBook(Book book){
    if (book == null){
        return BookConstant.FAILURE;
    }
    StringBuffer str = new StringBuffer();
    str.append(book.getNumber()).append(";").
            append(book.getName()).append(";")
            .append(book.getAuthor()).append(";").
            append(book.getPublisher()).append(";").
            append(book.getPrice()).append(";")
            .append(book.isBorrowed() ? "1":"0");
    return str.toString();
    }

}
