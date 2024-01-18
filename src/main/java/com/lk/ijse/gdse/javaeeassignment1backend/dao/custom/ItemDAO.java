package com.lk.ijse.gdse.javaeeassignment1backend.dao.custom;

import com.lk.ijse.gdse.javaeeassignment1backend.dao.CrudDAO;
import com.lk.ijse.gdse.javaeeassignment1backend.entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item, String > {

    boolean reduceItemQty(String itemCode, double qty) throws SQLException;
}
