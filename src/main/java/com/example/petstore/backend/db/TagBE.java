package com.example.petstore.backend.db;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
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
