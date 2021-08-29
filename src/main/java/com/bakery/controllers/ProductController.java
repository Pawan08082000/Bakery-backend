package com.bakery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bakery.exception.ProductNotFoundException;
import com.bakery.model.Product;
import com.bakery.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@PostMapping("/admin/product/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		System.out.println(product);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Ading Product Data");
		headers.add("type", "json data");
		Product nProduct = productService.addProduct(product);
		ResponseEntity<Product> responseProduct = new ResponseEntity<Product>(nProduct, headers, HttpStatus.ACCEPTED);
		return responseProduct;
	}
	
	@GetMapping("/user/product/get")
	public ResponseEntity<List<Product>> getProduct() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get All Products");
		List<Product> nProduct = productService.getAll();
		ResponseEntity<List<Product>> responseProduct = new ResponseEntity<List<Product>>(nProduct, HttpStatus.OK);

		return responseProduct;
	}
	
	@PutMapping("/admin/product/edit")
	public ResponseEntity<Void> updateProduct(@RequestBody Product product) {
		productService.updateProduct(product);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Updating Product Data");
		return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).build();
	}
	
	@DeleteMapping("/admin/product/delete/{productid}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("productid") String productId) {
		System.out.println(productId);
		try {
			productService.deleteProduct(productId);
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (ProductNotFoundException e) {
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		  }
	}
	
	@GetMapping("/user/products/title/{title}")
	public ResponseEntity<List<Product>> getProductByTitle(@PathVariable("title") String title) {
		List<Product> productList = productService.getByTitle(title);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Product by title");
		return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).body(productList);
	}
	
	@GetMapping("/user/product/{productid}")
	public ResponseEntity<List<Product>> getById(@PathVariable("productid") String id) {
		List<Product> productList = productService.getbyId(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Products by id");
		return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).body(productList);
	}

	
	@GetMapping("/user/products/category/{category}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category") String category) {
		List<Product> productList = productService.getByCategoryId(category);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Products by category");
		return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).body(productList);
	}
	
	@GetMapping("/user/products/price/{price}")
	public ResponseEntity<List<Product>> getProductByprice(@PathVariable("price") double price) {
		List<Product> productList = productService.getByPriceRange(price);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Products by price");
		return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).body(productList);
	}
	
	@GetMapping("/user/products/title/{title}/category/{category}/price/{price}")
	public ResponseEntity<List<Product>> getByTitleOrCatOrPriceRange(@PathVariable("title")String title,@PathVariable("category") String category,@PathVariable("price")double price){
		System.out.println(title+" "+category+" "+price);
		List<Product> productList = productService.getByTitlePriceCategory(title,category,price);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Products by price,title,category");
		return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).body(productList);
	}

	@RequestMapping(value = "/user/search", method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam("query")String word,
								@RequestParam(defaultValue = "0")int page, 
								@RequestParam(defaultValue ="20")int size) {
		try {
			Page<Product> products = this.productService.searchProduct(word, page, size);
			return ResponseEntity.ok(products);
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
