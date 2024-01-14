package com.lk.ijse.gdse.javaeeassignment1backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDTO implements Serializable {
    private String code;
    private String description;
    private int qty;
    private double unitPrice;
}
