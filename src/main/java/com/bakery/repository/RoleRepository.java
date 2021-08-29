package com.bakery.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bakery.model.Role;


@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

	Optional<Role> findByRoleType(String roleType);
}
