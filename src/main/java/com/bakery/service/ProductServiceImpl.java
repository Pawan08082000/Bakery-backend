package com.bakery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import com.bakery.exception.ProductNotFoundException;
import com.bakery.model.Product;
import com.bakery.model.ProductCategory;
import com.bakery.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	MongoTemplate mongoTemplate;

	public Product addProduct(Product product) {
		Product pro = productRepository.insert(product); 
		return pro;
	}

	public void updateProduct(Product product) {
		productRepository.save(product);
	}

	public void deleteProduct(String productId) {
		productRepository.deleteById(productId);
	}
	
	public List<Product> getbyId(String productId) throws ProductNotFoundException {
        
		return productRepository.findById(productId);
//				.orElseThrow(()-> new ProductNotFoundException("Invalid Id"));
	}

	public List<Product> getAll() {
		return productRepository.findAll();
	}

	public List<Product> getByCategoryId(String category) throws ProductNotFoundException {
		List<Product> productByCategory =  productRepository.findByCategoryId(category)
				.stream()
				.collect(Collectors.toList());
				if(productByCategory.isEmpty())
					throw new ProductNotFoundException("Category Not Found");
				
			     return productByCategory;
	}

	public List<Product> getByPriceRange(double price) throws ProductNotFoundException {
		List<Product> productByprice =  productRepository.findByPrice(price)
				.stream()
				.collect(Collectors.toList());
				if(productByprice.isEmpty())
					throw new ProductNotFoundException("Product for this price not found");
				
			     return productByprice;
	}

	public List<Product> getByTitle(String title) throws ProductNotFoundException {
		List<Product> productByTitle =  productRepository.findByTitle(title)
				.stream()
				.collect(Collectors.toList());
				if(productByTitle.isEmpty())
					throw new ProductNotFoundException("Product not found for this title");
				
			     return productByTitle;
	}

	@Override
	public List<Product> getByTitlePriceCategory(String title, String categoryName, double price)
			throws ProductNotFoundException {
		ProductCategory category = new ProductCategory();
		category.setName(categoryName);
		List<Product> productl =  productRepository.findByTitleOrCategoryOrPrice(title,category,price)
				.stream()
				.collect(Collectors.toList());
				if(productl.isEmpty())
					throw new ProductNotFoundException("Product not found");
				
			     return productl;
	}
	
	@Override
	public Page<Product> searchProduct(String word,int page, int size)
	{
		TextCriteria criteria = TextCriteria.forDefaultLanguage()
				  .matchingAny(word);
		long total = this.mongoTemplate.count(TextQuery.queryText(criteria), Product.class);
		Pageable pageable = PageRequest.of(page, size);
		Query query = TextQuery.queryText(criteria)
				  .sortByScore()
				  .with(pageable);
		List<Product> productList = mongoTemplate.find(query, Product.class);
		Page<Product> productPage = new PageImpl<>(productList, pageable, total);
		return productPage;	
	}

}
