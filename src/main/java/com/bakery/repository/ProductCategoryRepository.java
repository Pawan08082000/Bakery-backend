package com.bakery.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bakery.model.ProductCategory;

@Repository
public interface ProductCategoryRepository extends MongoRepository<ProductCategory ,Integer>  {
	
	List<ProductCategory> findByid(String productCategory);
}
