package com.example.petstore.backend.api.implementation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.petstore.backend.api.model.Category;
import com.example.petstore.backend.api.model.Pet;
import com.example.petstore.backend.api.model.Pet.StatusEnum;
import com.example.petstore.backend.api.model.Tag;
import com.example.petstore.backend.db.PetRepository;
import com.example.petstore.backend.db.TagBE;
import com.example.petstore.backend.db.PetBE;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PetControllerIntegrationTest {
	
	@Autowired
	private PetController petController;

//	private PetController petController = new PetController();

//	@Mock
//	private PetRepository pr;

	//TODO: integration tests: incomplete pet can be stored and restored
	
	//@Test
	void testfindPetsByTags() {
		//given
		Pet p0 = newPet("fido", "dog");
		Pet p1 = newPet("Klaus","good boy");
		Pet p2 = newPet("gary", "cat");
		//p0 is already there
		petController.addPet(p0);
		petController.addPet(p1);
		petController.addPet(p2);
		
		//pr.save(.)

		//save returns input
		assertNotNull(petController);
		List<String> tags = Arrays.asList("dog", "good boy");
		ResponseEntity<List<Pet>> r = petController.findPetsByTags(tags);
		
		//assertEquals(3, pr.count());
		assertEquals(2, r.getBody().size());
		
	}
	
	private long tagID;
	private long petID;
	
	private Pet newPet(String name, String... tagNames) {
		List<Tag> tags = new ArrayList<>();
		for(String s: tagNames) {
			tags.add(new Tag().name(s).id(tagID++));
		}
		
		Category c = new Category().id(0L).name("c");
		
		return new Pet().id(petID++).name(name).category(c).photoUrls(new ArrayList<String>()).tags(tags).status(StatusEnum.AVAILABLE);
	}

	@Test
	void testAddPet() {
		//when(pr.save(any(PetBE.class))).then(returnsFirstArg());
		Pet p1 = new Pet().id(5L).name("Klaus");
		assertNotNull(petController);
		ResponseEntity<Pet> r = petController.addPet(p1);
		assertEquals(new ResponseEntity<Pet>(p1, HttpStatus.OK), r);
	}

}
