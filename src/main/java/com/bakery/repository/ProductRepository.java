package com.bakery.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.bakery.model.Product;
import com.bakery.model.ProductCategory;

@Repository
public interface ProductRepository extends MongoRepository<Product ,Integer>{
	
	List<Product> findByTitle(String title);
	@Query("{'category.id':?0}")
	List<Product> findByCategoryId(String categoryid);
	List<Product> findByPrice(double price);
	@Query("{'id':?0}")
	List<Product> findById(String productid);
	Void deleteById(String productid);
	//@Query("$or:[{title:?0},{price:?2},{'category.name':?1}]")
	//@Query(value = "{ 'title' : ?0, 'category.name' : ?1, price : ?2 }", fields = "{ 'category.name' : 1 }")
    List<Product> findByTitleOrCategoryOrPrice(String title, ProductCategory category,double price);
    
}
