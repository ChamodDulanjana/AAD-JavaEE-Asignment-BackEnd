package com.lk.ijse.gdse.javaeeassignment1backend.dao.custom;

import com.lk.ijse.gdse.javaeeassignment1backend.dao.SuperDAO;
import com.lk.ijse.gdse.javaeeassignment1backend.dto.RecentOrderDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO  extends SuperDAO {

    List<RecentOrderDetailsDTO> getAll() throws SQLException;
}
