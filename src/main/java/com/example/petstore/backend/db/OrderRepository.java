package com.example.petstore.backend.db;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderBE, Long> {

}
