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
public class UserTests {

	@Autowired
	UserRepository userRepo;
	
	@BeforeEach
	public void setUp() {
		userRepo.deleteAll();
	}

	@Test
	public void saveUserWithID() {

		UserBE user = new UserBE(0L, "jabond", "james", "bond", "james.bond@mi6.co.uk", "123", "987654321", 0);
		userRepo.save(user);
		assertThat(userRepo.count()).isEqualTo(1);
	}
	
	@Test
	public void saveUserWithoutID() {

		UserBE user = new UserBE();
		user.setUsername("jabond");
		user.setFirstName("james");
		user.setLastName("bond");
		user.setEmail("james.bond@mi6.co.uk");
		user.setPassword("123");
		user.setPhone("987654321");
		user.setUserStatus(0);
		userRepo.save(user);
		assertThat(userRepo.count()).isEqualTo(1);

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
	
}
