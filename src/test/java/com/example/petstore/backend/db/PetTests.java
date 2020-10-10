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
public class PetTests {

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
	public void savePetWithId() {
		
		CategoryBE cat = new CategoryBE();
		cat.setId(0);
		cat.setName("c1");
		StatusEnum se = StatusEnum.AVAILABLE;
		List<String> urls = Arrays.asList();

		List<TagBE> tags = new ArrayList<TagBE>();
		tags.add(new TagBE(0, "tag-a"));
				
		PetBE pet0 = new PetBE(0, "a", cat, urls, tags, se);
		petRepo.save(pet0);
		
		assertThat(petRepo.count()).isEqualTo(1);

	}
	
	@Test
	public void savePetWithoutId() {
		
		CategoryBE cat = new CategoryBE();
		cat.setId(0);
		cat.setName("c1");
		StatusEnum se = StatusEnum.AVAILABLE;
		List<String> urls = Arrays.asList();

		List<TagBE> tags = new ArrayList<TagBE>();
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
		List<String> urls = Arrays.asList();

		List<TagBE> tags = new ArrayList<TagBE>();
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
	
}
