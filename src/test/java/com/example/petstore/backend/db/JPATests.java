package com.example.petstore.backend.db;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.petstore.backend.api.model.Pet.StatusEnum;
import com.example.petstore.mockups.Utils;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class JPATests {

	@Autowired
	PetRepository petRepo;
	
	@Autowired
	TagRepository tagRepo;

	@BeforeEach
	public void setUp() {
		petRepo.deleteAll();
		tagRepo.deleteAll();
	}

	@Test
	public void savetwoTags() {

		TagBE tag0 = new TagBE(0, "tag-a");
		TagBE tag1 = new TagBE(1, "tag-b");
		
		tagRepo.save(tag0);
		tagRepo.save(tag1);
	}
	
//	@Test
//	public void savePetWithExistingTag() {
//
//		CategoryBE cat = new CategoryBE();
//		cat.setId(0);
//		cat.setName("c1");
//		StatusEnum se = StatusEnum.AVAILABLE;
//		List<String> urls = Arrays.asList();
//
//		List<TagBE> tags = new ArrayList<TagBE>();
//		tags.add(new TagBE(0, "tag-a"));
//		
//		tagRepo.save(tags.get(0));
//		
//		PetBE pet0 = new PetBE(0, "a", cat, urls, tags, se);
//		petRepo.save(pet0);
//	}
//	
//	@Test
//	public void savePetWithExistingTags() {
//		
//		assertThat(tagRepo.count()).isEqualTo(0);
//		assertThat(petRepo.count()).isEqualTo(0);
//
//		CategoryBE cat = new CategoryBE();
//		cat.setId(0);
//		cat.setName("c1");
//		StatusEnum se = StatusEnum.AVAILABLE;
//		List<String> urls = Arrays.asList();
//
//		List<TagBE> tags = new ArrayList<TagBE>(2);
//		tags.add(new TagBE(0, "tag-a"));
//		tags.add(new TagBE(1, "tag-b"));
//		
//		tagRepo.save(tags.get(0));
//		tagRepo.save(tags.get(1));
//		
//		PetBE pet0 = new PetBE(0, "a", cat, urls, tags, se);
//		petRepo.save(pet0);
//	}
//
//	@Test
//	public void testC() {
//		long a = petRepo.count();
//
//		CategoryBE cat = new CategoryBE();
//		cat.setId(0);
//		cat.setName("c1");
//		StatusEnum se = StatusEnum.AVAILABLE;
//		List<String> urls = Arrays.asList();
//
//		List<TagBE> tags = new ArrayList<TagBE>(2);
//		tags.add(new TagBE(0, "tag-a"));
//		tags.add(new TagBE(1, "tag-b"));
//		
//		tagRepo.save(tags.get(0));
//		tagRepo.save(tags.get(1));
//		
//		PetBE pet0 = new PetBE(0, "a", cat, urls, tags, se);
//		petRepo.save(pet0);
//
//		tags.clear();
//		tags.add(new TagBE(2, "tag-c"));
//		tags.add(new TagBE(3, "tag-d"));
//
//		PetBE pet1 = new PetBE(1, "b", cat, urls, tags, se);
//		petRepo.save(pet1);
//	}
	
	@Test
	public void testZ() {
		List<PetBE> pets = Utils.makePets();		
		petRepo.save(pets.get(0));
		petRepo.save(pets.get(1));
		petRepo.save(pets.get(2));
		
		petRepo.saveAll(pets.subList(3, 5));
	}
	
	@Test
	public void findPetByTagTest() {
		//I don't know yet how to set up the database so this is just to see if exceptions re thrown
		List<String> tags = new ArrayList<>();
		tags.add("dog");
		petRepo.findPetsByAnyTag(tags);
	}
}
