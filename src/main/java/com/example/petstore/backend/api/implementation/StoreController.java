package com.example.petstore.backend.api.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.petstore.backend.api.StoreApi;
import com.example.petstore.backend.api.model.Order;
import com.example.petstore.backend.api.model.Pet.StatusEnum;
import com.example.petstore.backend.db.OrderBE;
import com.example.petstore.backend.db.OrderRepository;
import com.example.petstore.backend.db.PetBE;
import com.example.petstore.backend.db.PetRepository;
import com.example.petstore.backend.db.mappers.OrderMapper;
import com.example.petstore.backend.db.mappers.OrderMapperImpl;

@RestController
public class StoreController implements StoreApi {

	@Autowired
	PetRepository pr;
	
	@Autowired
	OrderRepository or;
	
	private final OrderMapper orderMapper = new OrderMapperImpl();
	
	@Override
	public ResponseEntity<Map<String, Integer>> getInventory() {
		//TODO make a status code table so this query can be handled by the db
		int[] statusCount = new int[StatusEnum.values().length];
		for(PetBE pbe : pr.findAll()) {
			statusCount[pbe.getStatus().ordinal()]++;
		}
		
		Map<String, Integer> m = new HashMap<>();
		for (int i = 0; i < statusCount.length; i++) {
			String statusLabel = StatusEnum.values()[i].name();
			int count = statusCount[i];
			m.put(statusLabel, count);
		}

		return new ResponseEntity<Map<String,Integer>>(m, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Order> placeOrder(@Valid Order order) {
		if(order.getId()<0 
				|| order.getPetId() <0 
				|| order.getQuantity()<0)
			return new ResponseEntity<Order>(HttpStatus.METHOD_NOT_ALLOWED);
		
		or.save(orderMapper.toBE(order));
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Order> getOrderById(Long orderId) {
		//TODO typo in spec
		//per spec (but thats a weird spec...)
		if(5 < orderId && orderId <= 10) {
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
		}
		
		if(orderId < 0) {
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
		}
		
		final ResponseEntity<Order> r;
		Optional<OrderBE> oo = or.findById(orderId);
		
		if(oo.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Order>(orderMapper.fromBE(oo.get()), HttpStatus.OK); 
	}
	
	@Override
	public ResponseEntity<Void> deleteOrder(Long orderId) {
		if(!or.existsById(orderId)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		or.deleteById(orderId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
