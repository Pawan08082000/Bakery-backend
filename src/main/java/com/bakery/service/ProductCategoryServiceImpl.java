package com.bakery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakery.model.ProductCategory;
import com.bakery.repository.ProductCategoryRepository;

@Service
public class ProductCategoryServiceImpl {

	@Autowired
	ProductCategoryRepository productCategoryRepository;
	

	public List<ProductCategory> getAll() {
		return productCategoryRepository.findAll();
	}
	
	public ProductCategory addProductCategory(ProductCategory productCategory) {
		ProductCategory category = productCategoryRepository.insert(productCategory); 
		return category;
	}
	
}
