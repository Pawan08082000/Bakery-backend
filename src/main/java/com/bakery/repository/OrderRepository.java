package com.bakery.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bakery.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order ,Integer> {

	List<Order> findByProductid(String product);

}
