package com.yts.system.util;

import com.alibaba.fastjson2.JSON;
import com.yts.system.constant.BookConstant;
import com.yts.system.model.Book;

import java.util.HashMap;
import java.util.Map;

public class Protocol {


    public static String addBookRequest(Book book){

        Map<String, Object> map = new HashMap<>();
        map.put(BookConstant.REQUEST, BookConstant.ADD);
        map.put(BookConstant.DATA, book);
        return JSON.toJSONString(map);
    }

    public static String updateBookRequest(Book book){

        Map<String, Object> map = new HashMap<>();
        map.put(BookConstant.REQUEST, BookConstant.UPDATE);
        map.put(BookConstant.DATA, book);
        return JSON.toJSONString(map);
    }

    public static String deleteBookRequest(int[] numbers){

        Map<String, Object> map = new HashMap<>();
        map.put(BookConstant.REQUEST, BookConstant.DELETE);
        Map<String, Integer> numberMap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            numberMap.put("" + numbers[i], Integer.valueOf(numbers[i]));
        }
        map.put(BookConstant.DATA, numberMap);
        return JSON.toJSONString(map);
    }

    public static String queryBookByNameRequest(String bookName, int pageNow, int pageSize){

        Map<String, Object> map = new HashMap<>();
        map.put(BookConstant.REQUEST, BookConstant.QUERY);
        Map<String, Object> query = new HashMap<>();
        query.put("bookName", bookName);
        query.put("pageNow", pageNow);
        query.put("pageSize", pageSize);
        map.put(BookConstant.DATA, query);
        return JSON.toJSONString(map);
    }

    public static String getTotalCountByNameRequest(String name){

        Map<String, Object> map = new HashMap<>();
        map.put(BookConstant.REQUEST, BookConstant.QUERY_TOTAL_COUNT);
        map.put(BookConstant.DATA, name);
        return JSON.toJSONString(map);
    }

    public static String queryBookByNumberRequest(int number){

        Map<String, Object> map = new HashMap<>();
        map.put(BookConstant.REQUEST, BookConstant.QUERY_BY_NUMBER);
        map.put(BookConstant.DATA, number);
        return JSON.toJSONString(map);
    }
}
