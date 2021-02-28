package com.example.petstore.backend.db;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.petstore.backend.api.model.Pet.StatusEnum;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class PetBE {
	
	@Id
	private long id;

	@NonNull
	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private CategoryBE category;
	
	@NonNull
	@ElementCollection
	private List<String> photoUrls;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "pet_tags", 
		joinColumns = @JoinColumn(name = "pet_id"), 
		inverseJoinColumns = @JoinColumn(name = "tag_id"))
	@OrderColumn(name = "id")
	private List<TagBE> tags;
	
	@Enumerated(EnumType.STRING)
	private StatusEnum status;

}
