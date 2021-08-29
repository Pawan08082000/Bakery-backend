package com.bakery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bakery.model.AppUser;

@Repository
public interface AppUserRepository  extends MongoRepository<AppUser, String>{

	AppUser findByUsername(String username);
}
