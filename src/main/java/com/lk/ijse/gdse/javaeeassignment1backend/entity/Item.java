package com.lk.ijse.gdse.javaeeassignment1backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Item implements SuperEntity{
    private String itemCode;
    private String description;
    private Double unitPrice;
    private int qtyOnHand;
}
