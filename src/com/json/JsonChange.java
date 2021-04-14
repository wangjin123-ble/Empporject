package com.json;
import	java.util.List;


import com.alibaba.fastjson.*;
import com.entity.table;


/**
 * 将json格式的数据转化为java中的对象
 */
public class JsonChange {
   // 通过调用阿里的fastjson包中的方法实现将json格式的数据转化为Java自定义的对象。
    public table JosnChangeJavaObject(String str){
        table table = JSON.parseObject(str, com.entity.table.class);
        return table;
    }

}
