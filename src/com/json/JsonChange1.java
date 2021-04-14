package com.json;


import com.entity.AllUser;
import com.alibaba.fastjson.JSON;


/**
 * 将json格式的数据转化为java中的List
 */
public class JsonChange1 {




    public AllUser JsonChange(String str){
        AllUser allUser = JSON.parseObject(str, AllUser.class);
        return allUser;

    }
}
