package com.example.petstore.backend.auth;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
//TODO renamed because error when same name as ..db.UserRepository, how to make it work with same name?
public interface UserAuthRepository extends CrudRepository<UserAuthBE, String> {
	
	public default Optional<String> getEncryptedPassword(String username) {
		return findById(username).map(x-> x.getEncodedPassword());
	}

}
