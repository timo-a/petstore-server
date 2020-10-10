package com.example.petstore.backend.api.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.petstore.backend.api.model.Pet.StatusEnum;
import com.example.petstore.backend.db.CategoryBE;
import com.example.petstore.backend.db.PetBE;
import com.example.petstore.backend.db.PetRepository;
import com.example.petstore.backend.db.TagBE;
import com.example.petstore.backend.db.TagRepository;
import com.example.petstore.mockups.Utils;

@RestController
public class SupplementaryController {
	
	@Autowired
	PetRepository pr;
	
	@Autowired
	TagRepository tr;
	
	@PostMapping(value = "/mock/pets")
	ResponseEntity<Void> mockupPets(){
		
		CategoryBE cat = new CategoryBE();
		cat.setId(0);
		cat.setName("c1");
		StatusEnum se = StatusEnum.AVAILABLE;
		List<String> urls = Arrays.asList();
				
		PetBE pet0;
		pet0 = new PetBE(0, "a", cat, urls, Arrays.asList(new TagBE(0, "tag-a")), se);
		pr.save(pet0);
		pet0 = new PetBE(1, "b", cat, urls, Arrays.asList(new TagBE(1, "tag-b")), se);
		pr.save(pet0);
		pet0 = new PetBE(2, "c", cat, urls, Arrays.asList(new TagBE(2, "tag-c")), se);
		pr.save(pet0);

//		List<PetBE> pets = Utils.makePets();		
//		pr.save(pets.get(0));
//		pr.save(pets.get(1));
//		pr.save(pets.get(2));
//		
//		pr.saveAll(pets.subList(3, 5));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/delete/pets")
	ResponseEntity<Void> deletePets(){
		pr.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/delete/tags")
	ResponseEntity<Void> deleteTags(){
		tr.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
