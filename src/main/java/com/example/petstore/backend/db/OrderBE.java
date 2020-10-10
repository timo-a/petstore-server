package com.example.petstore.backend.db;

import com.example.petstore.backend.api.model.Order.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderBE   {

	@Id
	private Long id;


	private Long petId;


	private Integer quantity;


	@org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
	private OffsetDateTime shipDate;

	@Enumerated(EnumType.STRING)
	public StatusEnum status;
	
	private Boolean complete;

}

