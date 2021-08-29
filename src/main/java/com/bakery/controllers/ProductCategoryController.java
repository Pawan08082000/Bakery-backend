package com.bakery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakery.model.ProductCategory;
import com.bakery.service.ProductCategoryServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductCategoryController {
	
private ProductCategoryServiceImpl productCategoryServiceImpl;
	
	@Autowired
	public ProductCategoryController(ProductCategoryServiceImpl productCategoryServiceImpl) {
		super();
		this.productCategoryServiceImpl = productCategoryServiceImpl;
	}
	
	@PostMapping("/admin/category/add")
	public ResponseEntity<ProductCategory> addProductCategory(ProductCategory productCategory) {
		HttpHeaders headers = new HttpHeaders();
		System.out.println(productCategory);
		headers.add("desc", "Ading product Category Data");
		headers.add("type", "json data");
		ProductCategory ncategory = productCategoryServiceImpl.addProductCategory(productCategory);
		ResponseEntity<ProductCategory> responseCategory = new ResponseEntity<ProductCategory>(ncategory, headers, HttpStatus.ACCEPTED);
		return responseCategory;
	}
	
	@GetMapping("/app/category/get")
	public ResponseEntity<List<ProductCategory>> getProductCategories() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get All Product Categories");
		List<ProductCategory> ncategory = productCategoryServiceImpl.getAll();
		ResponseEntity<List<ProductCategory>> responseCategory = new ResponseEntity<List<ProductCategory>>(ncategory, HttpStatus.OK);

		return responseCategory;
	}
	
	
}
