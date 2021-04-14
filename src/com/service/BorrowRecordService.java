package com.service;

import com.entity.BorrowRecord;

import java.util.List;

public interface BorrowRecordService {
    List<BorrowRecord> showAllRecord(String apply_mail);
}
