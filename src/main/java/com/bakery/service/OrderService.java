package com.bakery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bakery.exception.OrderNotFoundException;
import com.bakery.model.Order;


public interface OrderService {

	Order getbyId(int orderid) throws OrderNotFoundException;
	 
	Order addOrder(Order Order);
    void updateOrder(Order Order);
    void deleteOrder(String orderId);
    List<Order> getAll();
    List<Order> getByProductid(String productid)throws OrderNotFoundException;

}
