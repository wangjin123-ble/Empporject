package com.service.impl;

import com.dao.ImgDao;
import com.dao.impl.ImgDaoImpl;
import com.entity.Img;
import com.entity.User;
import com.service.ImgService;
import com.utils.DbUtils;

import java.util.List;

public class ImgServiceImpl implements ImgService {
    ImgDao imgDao = new ImgDaoImpl();

    @Override
    public int add(Img img) {
        imgDao = new ImgDaoImpl();
        try {
            DbUtils.begin();
            imgDao.insert(img);
            DbUtils.commit();
            return 1;
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Img> getImgs(int user_id) {
        List<Img> result = null;
        try {
            DbUtils.begin();
            List<Img> imgs = imgDao.select(user_id);
            if (imgs != null) {
                result = imgs;

            }
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return result;
    }
}
