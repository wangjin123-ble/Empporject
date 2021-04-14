package com.dao.impl;

import com.dao.BorrowRecordDao;
import com.entity.Book;
import com.entity.BorrowRecord;
import com.utils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BorrowRecordDaoImpl implements BorrowRecordDao {
    QueryRunner queryRunner = new QueryRunner();

    @Override
    public List<BorrowRecord> select(String apply_mail) {
        try {
            List<BorrowRecord> borrowRecordList = queryRunner.query(DbUtils.getConnection(), "select * from borrowrecord where apply_mail = ?; ", new BeanListHandler<BorrowRecord>(BorrowRecord.class), apply_mail);
            return borrowRecordList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
