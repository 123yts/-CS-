package com.yts.system.controller;

import com.yts.system.constant.BookConstant;
import com.yts.system.dao.BookDao;
import com.yts.system.model.Book;
import com.yts.system.util.Parser;
import com.yts.system.util.Protocol;

import java.util.List;

public class BookController {

    public String response(String request){

        BookDao bookDao = new BookDao();
        // & 分割请求类型和数据
        String[] strings = Parser.getActionAndData(request);
        switch (strings[0]){
            case BookConstant.ADD:
                return bookDao.insertBook(Parser.getBook(strings[1])) ? BookConstant.SUCCESS : BookConstant.FAILURE;
            case BookConstant.DELETE:
                return bookDao.deleteBook(Parser.getBookNumbers(strings[1])) ? BookConstant.SUCCESS : BookConstant.FAILURE;
            case BookConstant.UPDATE:
                return bookDao.updateBook(Parser.getBook(strings[1])) ? BookConstant.SUCCESS : BookConstant.FAILURE;
            case BookConstant.QUERY:
                String[] strs = Parser.getBookQuery(strings[1]);
                String queryName = "";
//                for (String s : strs) {
//                    System.out.println("s: " + s);
//                }
                if (!"#".equals(strs[0])) queryName = strs[0];
                List<Book> bookList = bookDao.getBookListByName(queryName, Integer.parseInt(strs[1]), Integer.parseInt(strs[2]));
                return  bookList.size() > 0 ? Protocol.retBookList(bookList) : BookConstant.FAILURE;
            case BookConstant.QUERY_TOTAL_COUNT:
                String queryName2 = "";
                if (!"#".equals(strings[1])) queryName2 = strings[1];
                return  "" + bookDao.getBookTotalCountByName(queryName2);
            case BookConstant.QUERY_BY_NUMBER:
                return  Protocol.retBook(bookDao.getBookByNumber(Parser.getBookNumber(request)));
            default:
                return BookConstant.FAILURE;
        }

    }
}
