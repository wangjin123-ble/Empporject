package com.dao.impl;

import com.utils.DbUtils;
import com.dao.searchDao;
import com.entity.search;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class searchDaoImpl implements searchDao {


    @Override
    public List<search> selectAll() throws SQLException {
        Connection conn = null;
        conn = DbUtils.getConnection();


        QueryRunner qr = new QueryRunner();

            String query = "Select * from examine where types= '维修' and (pass = 1 or pass=2) ";
           List results = (List) qr.query(conn, query, new MapListHandler()); //使用QueryRunner类中的query方法把查询结果封装在List中，单条记录存在map中

// TODO Auto-generated catch block

        return results;
    }

}