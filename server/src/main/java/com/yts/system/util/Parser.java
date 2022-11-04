package com.yts.system.util;

import com.alibaba.fastjson2.JSONObject;
import com.yts.system.model.Book;

import java.util.*;

public class Parser {

    public static Map getActionAndData(String request){

        Map map = JSONObject.parseObject(request, Map.class);
        return map;
    }

    public static Book getBook(Object bookStr){
        return JSONObject.parseObject(JSONObject.toJSONString(bookStr), Book.class);
    }

    public static int[] getBookNumbers(Object numbers){
        Map<String, Integer> map = (Map<String, Integer>)numbers;
        int[] nums = new int[map.size()];
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        int i=0;
        while (iterator.hasNext()){
            nums[i] = iterator.next().getValue();
        }
        return nums;
    }

    // bookName、pageNow、 pageSize
    public static String[] getBookQuery(Object query){

        JSONObject jsonObject = (JSONObject)query;
        String[] strs = new String[3];
        strs[0] = (String) jsonObject.get("bookName");
        Integer pageNow = (Integer)jsonObject.get("pageNow");
        Integer pageSize = (Integer)jsonObject.get("pageSize");
        strs[1] = pageNow + "";
        strs[2] = pageSize + "";
        return strs;
    }

    public static int getBookNumber(Object request){
        Integer number = (Integer) request;
        return number.intValue();
    }

}
