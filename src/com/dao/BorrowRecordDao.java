package com.dao;

import com.entity.BorrowRecord;

import java.util.List;

public interface BorrowRecordDao {
    public List<BorrowRecord> select(String apply_mail);
}
