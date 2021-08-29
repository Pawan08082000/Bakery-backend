package com.bakery.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
public class Product {
	@Id
	private String id;
	@Field
	private String title;
	@Field
	private String description;
	@Field   
	private double price;
	@Field   
	private String ratings;
	@Field   
	private Integer unitInStock;
	@Field
	private String productImagePath;
	@DBRef
	private ProductCategory category;
	@Field
	@DateTimeFormat
	private LocalDate createdAt;
	@Field
	@DateTimeFormat
	private LocalDate  updatedAt;
}
