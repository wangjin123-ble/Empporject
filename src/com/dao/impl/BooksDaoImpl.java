package com.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.dao.BooksDao;
import com.entity.Allbooks;

import com.utils.DbUtils;

import java.sql.SQLException;
import java.util.List;

public class BooksDaoImpl implements BooksDao {
    private QueryRunner queryRunner=new QueryRunner();


    @Override
    public List<Allbooks> selectAll() {
        try {
           List<Allbooks> allbooks=queryRunner.query(DbUtils.getConnection(),"select * from allbooks ;", new BeanListHandler<>(Allbooks.class));
        return allbooks;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }










    @Override
    public int delete(int ID ) {
        try {
            int result=queryRunner.update(DbUtils.getConnection(),"delete from allbooks where ID = ?;",ID);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  0;
    }




    @Override
    public List<Allbooks> select(String bookname) {

            try {
                List<Allbooks> books1 = queryRunner.query(DbUtils.getConnection(),"select * from allbooks where name = ?; ", new BeanListHandler<Allbooks>(Allbooks.class),bookname);
                return books1;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return null;
        }

    @Override
    public List<Allbooks> select(int ID) {
        try {
            List<Allbooks> allbooks=queryRunner.query(DbUtils.getConnection(),"select * from allbooks where ID = ?;",new BeanListHandler<>(Allbooks.class),ID);
            return allbooks;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  null;
    }

    @Override
    public int update(int ID) {
        try {
            int result=queryRunner.update(DbUtils.getConnection(),"UPDATE allbooks SET article='图书',name=?,author=?,variety=?,time=?,price=?,buy_time=?,number=?,place=?,manager=?,department=?,introducescore=? WHERE ID=?;",ID);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  0;
    }


}
