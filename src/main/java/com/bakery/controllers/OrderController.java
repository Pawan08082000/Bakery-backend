package com.bakery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakery.exception.OrderNotFoundException;
import com.bakery.model.Order;
import com.bakery.service.OrderService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class OrderController {
 
private OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
	
	@PostMapping("/user/order/add")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		HttpHeaders headers = new HttpHeaders();
		System.out.println(order);
		headers.add("desc", "Ading Order Data");
		headers.add("type", "json data");
		Order nOrder = orderService.addOrder(order);
		ResponseEntity<Order> responseOrder = new ResponseEntity<Order>(nOrder, headers, HttpStatus.ACCEPTED);
		return responseOrder;
	}
	
	@GetMapping("/user/order/get")
	public ResponseEntity<List<Order>> getOrder() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get All Orders");
		List<Order> nOrder = orderService.getAll();
		ResponseEntity<List<Order>> responseOrder = new ResponseEntity<List<Order>>(nOrder, HttpStatus.OK);

		return responseOrder;
	}
	
	@PutMapping("/user/order/update")
	public ResponseEntity<Void> updateOrder(@RequestBody Order order) {
		orderService.updateOrder(order);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Updating Order Data");
		return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).build();
	}
	
	@GetMapping("/user/orders/product/{productid}")
	public ResponseEntity<List<Order>> getOrderByProduct(@PathVariable("productid") String productid) {
		List<Order> OrderList = orderService.getByProductid(productid);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Order by product");
		return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).body(OrderList);
	}
}
