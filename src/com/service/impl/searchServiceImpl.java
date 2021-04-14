package com.service.impl;


import com.dao.impl.searchDaoImpl;
import com.dao.searchDao;
import com.service.searchService;

import java.sql.SQLException;
import java.util.List;

public class searchServiceImpl implements searchService {
     searchDao searchDao = new searchDaoImpl();
    @Override
    public List showAllSearch() throws SQLException {

            List searches = searchDao.selectAll();

            return searches;
  }
}