package com.bakery.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Order {
	@Id
	private String orderId;
	@Field
	private String productid;
	@Field
	private Integer appuserid;
	@Field	
	private Integer quantity;
	@Field
	private double price;
	@Field
	@DateTimeFormat
	private LocalDate  orderDate;
	
	@Field
	private ZonedDateTime timestamp;
	
	@Field
	private double discount;
	
	
	

}
