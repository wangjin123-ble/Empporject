package com.dao.impl;

import com.dao.ImgDao;
import com.entity.BorrowRecord;
import com.entity.Img;
import com.entity.User;
import com.utils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ImgDaoImpl implements ImgDao {
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public void insert(Img img) {
        String sql = "insert into img (user_id,image_path,old_name) values (?,?,?)";
        Object[] params = {img.getUser_id(), img.getImage_path(), img.getOld_name()};
        try {
            queryRunner.insert(DbUtils.getConnection(), sql, new ScalarHandler<>(), params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Img> select(int user_id) {
        try {
            List<Img> imgs = queryRunner.query(DbUtils.getConnection(), "select * from img where user_id = ?; ", new BeanListHandler<Img>(Img.class), user_id);
            return imgs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
