package com.example.petstore.backend.api.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.petstore.backend.api.PetApi;
import com.example.petstore.backend.api.model.ModelApiResponse;
import com.example.petstore.backend.api.model.Pet;
import com.example.petstore.backend.api.model.Pet.StatusEnum;
import com.example.petstore.backend.db.PetBE;
import com.example.petstore.backend.db.PetRepository;
import com.example.petstore.backend.db.mappers.PetMapper;
import com.example.petstore.backend.db.mappers.PetMapperImpl;

//@Service
@RestController
public class PetController implements PetApi {
	
	@Autowired
	PetRepository pr;
	
	private final PetMapper petMapper = new PetMapperImpl();
	
	@Override
	public ResponseEntity<Pet> updatePet(@Valid Pet pet) {
		
		long id = pet.getId();
		
		if(id < 0) {
			System.out.println(0);
			return new ResponseEntity<Pet>(HttpStatus.BAD_REQUEST);
		}
		Optional<PetBE> oldBEOptional = pr.findById(id);
		
		if(oldBEOptional.isEmpty()) {
			System.out.println(1);
			return new ResponseEntity<Pet>(HttpStatus.NOT_FOUND);
		} 
		
		if(/* Validation Error TODO: find out when it occurs*/ false) {
			System.out.println(2);
			return new ResponseEntity<Pet>(HttpStatus.METHOD_NOT_ALLOWED);
		} 
	
		PetBE oldBE = oldBEOptional.get();
		Pet old = petMapper.fromBE(oldBE);
		pr.save(petMapper.toBE(pet));
		return new ResponseEntity<Pet>(old, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Pet> addPet(@Valid Pet pet) {
	
		PetBE petBE = petMapper.toBE(pet);
		pr.save(petBE);
		return new ResponseEntity<Pet>(pet, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<Pet>> findPetsByStatus(@Valid String status) {
		StatusEnum e;
		try {
			e = StatusEnum.valueOf(status);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity<List<Pet>>(HttpStatus.BAD_REQUEST);
		}
		List<Pet> pets = new ArrayList<Pet>();
		for (PetBE pet : pr.findPetByStatusBK(e)) {
			pets.add(petMapper.fromBE(pet));
		}
		return new ResponseEntity<List<Pet>>(pets, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<Pet>> findPetsByTags(@Valid List<String> tags) {
		// TODO: clarify whether OR or AND
		
		//TODO: what is an invalid tag name (400)
		
		List<Pet> filtered = StreamSupport.stream(pr.findPetsByAnyTag(tags).spliterator(), false)
				                          .map(petMapper::fromBE)
		                                  .collect(Collectors.toList());
		
		return new ResponseEntity<List<Pet>>(filtered, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Pet> getPetById(Long petId) {

		if(petId < 0) {
			return new ResponseEntity<Pet>(HttpStatus.BAD_REQUEST);
		}
		Optional<PetBE> petBEOptional = pr.findById(petId);
		
		if(petBEOptional.isEmpty()) {
			return new ResponseEntity<Pet>(HttpStatus.NOT_FOUND);
		} 
			
		Pet pet = petMapper.fromBE(petBEOptional.get());
		return new ResponseEntity<Pet>(pet, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> updatePetWithForm(Long petId, @Valid String name, @Valid String status) {

		//TODO: which params are needed to identify, which are updated?
		
		//TODO: what is invalid input
		//TODO: no success specified?
		
		//TODO: check if necessary
		if(petId == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		if(name == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<PetBE> oPetBE = pr.findById(petId);
		
		if(oPetBE.isEmpty()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Void> deletePet(Long petId, String apiKey) {
		//TODO: use apiKey
		
		pr.deleteById(petId);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ModelApiResponse> uploadFile(Long petId, @Valid String additionalMetadata,
			@Valid Resource body) {
		// TODO what are the parameters?
		// TODO what is the response?
		Optional<PetBE> oPetBE = pr.findById(petId);
		
		if(oPetBE.isEmpty()) {
			return new ResponseEntity<ModelApiResponse>(HttpStatus.NOT_FOUND);
		}
		
		PetBE petBE = oPetBE.get();
		pr.delete(petBE);
		petBE.getPhotoUrls().add(additionalMetadata);
		pr.save(petBE);
		ModelApiResponse mar = new ModelApiResponse();
		mar.setMessage("Now idea what this is supposed do");
		return new ResponseEntity<>(mar, HttpStatus.OK);
	}
}
