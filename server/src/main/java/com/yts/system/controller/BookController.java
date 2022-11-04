package com.yts.system.controller;

import com.yts.system.constant.BookConstant;
import com.yts.system.dao.BookDao;
import com.yts.system.model.Book;
import com.yts.system.util.Parser;
import com.yts.system.util.Protocol;

import java.util.List;
import java.util.Map;

public class BookController {

    public String response(String request){

        BookDao bookDao = new BookDao();
        // 请求类型和数据
        Map map = Parser.getActionAndData(request);
        switch ((String) map.get(BookConstant.REQUEST)){
            case BookConstant.ADD:
                return bookDao.insertBook(Parser.getBook(map.get(BookConstant.DATA))) ? BookConstant.SUCCESS : BookConstant.FAILURE;
            case BookConstant.DELETE:
                return bookDao.deleteBook(Parser.getBookNumbers(map.get(BookConstant.DATA))) ? BookConstant.SUCCESS : BookConstant.FAILURE;
            case BookConstant.UPDATE:
                return bookDao.updateBook(Parser.getBook(map.get(BookConstant.DATA))) ? BookConstant.SUCCESS : BookConstant.FAILURE;
            case BookConstant.QUERY:
                String[] strs = Parser.getBookQuery(map.get(BookConstant.DATA));
                String queryName = "";
//                for (String s : strs) {
//                    System.out.println("s: " + s);
//                }
                if (!"#".equals(strs[0])) queryName = strs[0];
                List<Book> bookList = bookDao.getBookListByName(queryName, Integer.parseInt(strs[1]), Integer.parseInt(strs[2]));
                return  bookList.size() > 0 ? Protocol.retBookList(bookList) : BookConstant.FAILURE;
            case BookConstant.QUERY_TOTAL_COUNT:
                String queryName2 = "";
                if (!"#".equals((String) map.get(BookConstant.DATA))) queryName2 = (String) map.get(BookConstant.DATA);
                return  "" + bookDao.getBookTotalCountByName(queryName2);
            case BookConstant.QUERY_BY_NUMBER:
                return  Protocol.retBook(bookDao.getBookByNumber(Parser.getBookNumber(map.get(BookConstant.DATA))));
            default:
                return BookConstant.FAILURE;
        }

    }
}
