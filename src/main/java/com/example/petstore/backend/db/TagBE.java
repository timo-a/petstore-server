package com.example.petstore.backend.db;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "tags")
public class TagBE {

	@Id
	@NonNull
	private long id;
	
	@NonNull
	private String name;
	
	@ManyToMany
	private List<PetBE> pets;
	
}
