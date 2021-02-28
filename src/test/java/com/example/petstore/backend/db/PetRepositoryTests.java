package com.example.petstore.backend.db;

import com.example.petstore.backend.api.model.Pet;
import com.example.petstore.backend.api.model.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
public class PetRepositoryTests {

	@Autowired
	PetRepository petRepo;
	
	@Autowired
	TagRepository tagRepo;

	@Autowired

	@BeforeEach
	public void setUp() {
		petRepo.deleteAll();
		tagRepo.deleteAll();
	}

	@Test
	public void savePetWithId() {

		//create dummy pet
		CategoryBE cat = new CategoryBE(0L, "good pet",null);
		//cat.setId(0);
		//cat.setName("good pet");
		StatusEnum se = StatusEnum.AVAILABLE;
		List<String> urls = Collections.emptyList();

		List<TagBE> tags = new ArrayList<>();
		tags.add(new TagBE(0, "tag-a"));
				
		PetBE pet0 = new PetBE(0, "a", cat, urls, tags, se);

		// save it
		petRepo.save(pet0);

		assertThat(petRepo.count()).isEqualTo(1);
		assertThat(tagRepo.count()).isEqualTo(1);

	}
	
	@Test
	public void savePetWithoutId() {
		
		CategoryBE cat = new CategoryBE();
		cat.setId(0);
		cat.setName("c1");
		StatusEnum se = StatusEnum.AVAILABLE;
		List<String> urls = Collections.emptyList();

		List<TagBE> tags = new ArrayList<>();
		tags.add(new TagBE(0, "tag-a"));
				
		PetBE pet0 = new PetBE();
		pet0.setName("a");
		pet0.setCategory(cat);
		pet0.setPhotoUrls(urls);
		pet0.setTags(tags);
		pet0.setStatus(se);
		petRepo.save(pet0);
		
		assertThat(petRepo.count()).isEqualTo(1);

	}

	@Test
	public void savePetWithoutAnyId() {

		CategoryBE cat = new CategoryBE();
		cat.setName("c1");
		StatusEnum se = StatusEnum.AVAILABLE;
		List<String> urls = Collections.emptyList();

		List<TagBE> tags = new ArrayList<>();
		tags.add(new TagBE());
		tags.get(0).setName("tag-a");

		PetBE pet0 = new PetBE();
		pet0.setName("a");
		pet0.setCategory(cat);
		pet0.setPhotoUrls(urls);
		pet0.setTags(tags);
		pet0.setStatus(se);
		petRepo.save(pet0);

		assertThat(petRepo.count()).isEqualTo(1);

	}

	@Test
	public void savePetCategory() {

		PetBE pet = new PetBE();
		pet.setId(0L);
		pet.setName("string");

		CategoryBE cat = new CategoryBE();
		cat.setId(0L);
		cat.setName("string");

		pet.setCategory(cat);
		pet.setPhotoUrls(Collections.singletonList("string"));
		TagBE tag = new TagBE();
		tag.setId(0);
		tag.setName("string");
		pet.setTags(Collections.singletonList(tag));
		pet.setStatus(StatusEnum.AVAILABLE);

		petRepo.save(pet);

		assertThat(petRepo.count()).isEqualTo(1);

	}

}
