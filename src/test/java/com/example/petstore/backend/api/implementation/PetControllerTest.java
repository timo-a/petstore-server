package com.example.petstore.backend.api.implementation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {
	
	@InjectMocks
	private PetController petController;

	@Mock
	private PetRepository pr;

	//TODO: integration tests: incomplete pet can be stored and restored
	
	@Test
	void testUpdatePetReturnsOldPet() {
		//given
		Pet p0 = new Pet().id(5L).name("fido").tags(new ArrayList<Tag>()).status(StatusEnum.AVAILABLE);
		PetBE pbe0 = new PetBE(5L,"fido", null, new ArrayList<String>(), new ArrayList<TagBE>(), StatusEnum.AVAILABLE);
		Pet p1 = new Pet().id(5L).name("Klaus");
		//p0 is already there
		given(pr.findById(p1.getId())).willReturn(Optional.of(pbe0));
		//save returns input
		when(pr.save(any(PetBE.class))).then(returnsFirstArg());
		
		assertNotNull(petController);
		ResponseEntity<Pet> r = petController.updatePet(p1);
		assertEquals(new ResponseEntity<Pet>(p0, HttpStatus.OK), r);
		
	}

	@Test
	void testAddPet() {
		when(pr.save(any(PetBE.class))).then(returnsFirstArg());
		Pet p1 = new Pet().id(5L).name("Klaus");
		assertNotNull(petController);
		ResponseEntity<Pet> r = petController.addPet(p1);
		assertEquals(new ResponseEntity<Pet>(p1, HttpStatus.OK), r);
	}

}
