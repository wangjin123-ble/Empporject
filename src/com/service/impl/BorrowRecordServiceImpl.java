package com.service.impl;

import com.dao.BorrowRecordDao;
import com.dao.impl.BorrowRecordDaoImpl;
import com.entity.BorrowRecord;
import com.service.BorrowRecordService;
import com.utils.DbUtils;

import java.util.List;

public class BorrowRecordServiceImpl implements BorrowRecordService {

    BorrowRecordDao borrowRecordDao = new BorrowRecordDaoImpl();

    @Override
    public List<BorrowRecord> showAllRecord(String apply_mail) {
        List<BorrowRecord> result = null;
        try {
            DbUtils.begin();
            result = borrowRecordDao.select(apply_mail);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return result;
    }
}
