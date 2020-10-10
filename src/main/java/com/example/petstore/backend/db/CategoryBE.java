package com.example.petstore.backend.db;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class CategoryBE {

	@Id
	private long id;
	
	private String name;
	
	//@Transient TODO test out
	@OneToMany(mappedBy = "category")
	private List<PetBE> pet;
}
