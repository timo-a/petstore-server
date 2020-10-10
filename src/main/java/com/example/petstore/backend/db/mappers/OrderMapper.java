package com.example.petstore.backend.db.mappers;

import org.mapstruct.Mapper;

import com.example.petstore.backend.api.model.Order;
import com.example.petstore.backend.db.OrderBE;

@Mapper
public interface OrderMapper {

	OrderBE toBE(Order s);
	
	Order fromBE(OrderBE s);
}
