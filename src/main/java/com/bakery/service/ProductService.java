package com.bakery.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bakery.exception.ProductNotFoundException;
import com.bakery.model.Product;


public interface ProductService{

	List<Product> getbyId(String id) throws ProductNotFoundException;
	
	Product addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(String productId);
    List<Product> getAll();
    List<Product> getByCategoryId(String category)throws ProductNotFoundException;
    List<Product> getByPriceRange(double price)throws ProductNotFoundException;
    List<Product> getByTitle(String title)throws ProductNotFoundException;
    List<Product> getByTitlePriceCategory(String title, String category,double price)throws ProductNotFoundException;

	Page<Product> searchProduct(String word, int page, int size);

}
