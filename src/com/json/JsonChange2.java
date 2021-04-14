package com.json;


import com.alibaba.fastjson.JSON;
import com.entity.Change;


/**
 * 将json格式的数据转化为java中的List
 */
public class JsonChange2 {




    public Change JsonChange(String str){

        Change change= JSON.parseObject(str,Change.class);
        return change;

    }


}
