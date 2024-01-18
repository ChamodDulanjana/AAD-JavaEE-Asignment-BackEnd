package com.lk.ijse.gdse.javaeeassignment1backend.api.util;

import com.lk.ijse.gdse.javaeeassignment1backend.dto.CustomerDTO;
import com.lk.ijse.gdse.javaeeassignment1backend.dto.ItemDTO;
import com.lk.ijse.gdse.javaeeassignment1backend.dto.OrderDTO;
import com.lk.ijse.gdse.javaeeassignment1backend.dto.OrderDetailsDTO;
import com.lk.ijse.gdse.javaeeassignment1backend.entity.Customer;
import com.lk.ijse.gdse.javaeeassignment1backend.entity.Item;
import com.lk.ijse.gdse.javaeeassignment1backend.entity.Order;

import java.sql.Date;
import java.util.ArrayList;

public class Convertor {
    public CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getCustomerID(), customer.getName(), customer.getAddress(), customer.getContact());
    }

    public Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getContact());
    }

    public ItemDTO formItem(Item item){
        return new ItemDTO(item.getItemCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
    }

    public Item toItem(ItemDTO itemDTO){
        return new Item(itemDTO.getItemCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand());
    }

    public OrderDTO fromOrder(Order order){
        return new OrderDTO(order.getOrderID(), order.getCustomerID(), String.valueOf(order.getOrderDate()), new ArrayList<>());
    }

    public Order toOrder(OrderDTO orderDTO){
        return new Order(orderDTO.getOrderID(), orderDTO.getCustomerID(), Date.valueOf(orderDTO.getOrderDate()));
    }

    public OrderDetailsDTO fromOrderDetails(com.lk.ijse.gdse.javaeeassignment1backend.entity.OrderDetails orderDetails){
        return new OrderDetailsDTO(orderDetails.getOrderID(), orderDetails.getItemCode(), orderDetails.getUnitPrice(), orderDetails.getQty(), orderDetails.getTotal());
    }

    public com.lk.ijse.gdse.javaeeassignment1backend.entity.OrderDetails toOrderDetails(OrderDetailsDTO orderDetailsDTO){
        return new com.lk.ijse.gdse.javaeeassignment1backend.entity.OrderDetails(orderDetailsDTO.getOrderID(), orderDetailsDTO.getItemCode(), orderDetailsDTO.getUnitPrice(), orderDetailsDTO.getQty(), orderDetailsDTO.getTotal());
    }
}
