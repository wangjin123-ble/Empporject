package com.json;


import com.alibaba.fastjson.JSON;
import com.entity.*;

import java.util.ArrayList;

/**
 *
 * 将ArrayList集合转化为json格式后保存在一个字符串对象里
 */
public class ChangeJson {


    public String ChangeJson(ArrayList list){

        String str = JSON.toJSONString(list);
//        System.out.println(str);
        return str;
    }


}

