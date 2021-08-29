package com.bakery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class ProductCategory {

	@Id
	private String id;
	@Field
	private String name;
	
	@Field
	private double maxRewardPoint;

}
