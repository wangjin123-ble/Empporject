package com.dao;

import com.entity.search;

import java.sql.SQLException;
import java.util.List;

public interface searchDao {
    public List<search> selectAll() throws SQLException;
}
