package com.lk.ijse.gdse.javaeeassignment1backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderDetails implements SuperEntity{
    private String orderID;
    private String itemCode;
    private double unitPrice;
    private int qty;
    private double total;
}

