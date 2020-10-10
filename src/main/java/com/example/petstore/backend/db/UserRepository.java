package com.example.petstore.backend.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserBE, Long> {

	public List<UserBE> findByUsername(String username);

//	public default void deleteFirstByUsername(String username) {
//		List<UserBE> all = findByUsername(username);
//		if(!all.isEmpty()) {
//			delete(all.get(0));
//		}
//	}

	public Optional<UserBE> findFirstByUsername(String username);
	
	public default Optional<UserBE> findFirstByUsernameBK(String username) {
		List<UserBE> all = findByUsername(username);
		if(all.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(all.get(0));
		}
	}

	/**
	 * If a User with the passed username exists, update it
	 * otherwise return false.
	 * @param ube
	 * @return
	 */
	public default boolean updateExistingByUsername(String username, UserBE user) {
		Optional<UserBE> ou = findFirstByUsername(username);
		if(ou.isPresent()) {
			delete(ou.get());
			save(user);
			return true;
		}
		return false;
	}	

	/**
	 * If a User with the passed username exists, delete it
	 * otherwise return false.
	 * @param ube
	 * @return
	 */
	public default boolean deleteExistingByUsername(String username) {
		Optional<UserBE> ou = findFirstByUsername(username);
		if(ou.isPresent()) {
			delete(ou.get());
			return true;
		}
		return false;
	}	
}
