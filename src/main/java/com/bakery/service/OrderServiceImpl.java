package com.bakery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakery.exception.OrderNotFoundException;
import com.bakery.model.Order;
import com.bakery.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public Order getbyId(int orderid) throws OrderNotFoundException {
		return orderRepository.findById(orderid)
				.orElseThrow(()-> new OrderNotFoundException("Invalid Id"));
	}

	@Override
	public Order addOrder(Order order) {
		Order pro = orderRepository.insert(order); 
		return pro;
	}

	@Override
	public void updateOrder(Order order) {
		orderRepository.save(order);		
	}

	@Override
	public List<Order> getAll() {
		return orderRepository.findAll();
	}

	@Override
	public List<Order> getByProductid(String productid) throws OrderNotFoundException {
		List<Order> orderByProduct =  orderRepository.findByProductid(productid)
				.stream()
				.collect(Collectors.toList());
				if(orderByProduct.isEmpty())
					throw new OrderNotFoundException("Order Not Found with this product");
				
			     return orderByProduct;
	}

	@Override
	public void deleteOrder(String orderId) {
		// TODO Auto-generated method stub
		
	}

}
