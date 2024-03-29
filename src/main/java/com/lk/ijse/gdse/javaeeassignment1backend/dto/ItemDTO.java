package com.lk.ijse.gdse.javaeeassignment1backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ItemDTO implements Serializable {
    private String itemCode;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
}
