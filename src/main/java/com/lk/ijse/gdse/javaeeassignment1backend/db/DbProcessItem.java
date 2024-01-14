package com.lk.ijse.gdse.javaeeassignment1backend.db;

import com.lk.ijse.gdse.javaeeassignment1backend.dto.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DbProcessItem {

    private static final String SAVE_ITEM_DATA = "INSERT INTO item (CODE,DESCRIPTION,QTY,UNITPRICE) VALUES (?,?,?,?)";


    public static void saveItemData(ItemDTO itemDTO, Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement(SAVE_ITEM_DATA);
            ps.setString(1, itemDTO.getCode());
            ps.setString(2, itemDTO.getDescription());
            ps.setInt(3, itemDTO.getQty());
            ps.setDouble(4, itemDTO.getUnitPrice());

            if (ps.executeUpdate() != 0) {
                System.out.println("Data saved");
            } else {
                System.out.println("Failed to save");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
