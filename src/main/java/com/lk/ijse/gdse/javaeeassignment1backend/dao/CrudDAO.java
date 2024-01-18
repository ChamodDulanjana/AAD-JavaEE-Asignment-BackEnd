package com.lk.ijse.gdse.javaeeassignment1backend.dao;

import com.lk.ijse.gdse.javaeeassignment1backend.entity.SuperEntity;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T extends SuperEntity, ID extends Serializable> extends SuperDAO{

    List<T> getAll() throws SQLException;

    T save( T entity) throws SQLException;

    T update(T entity) throws SQLException;

    void delete(ID pk) throws SQLException;

}
