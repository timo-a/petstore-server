package com.example.petstore.stacko.jpa0;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class StudentController {
	
	@Autowired
	PupilRepository pupilRepo;
	
	@Autowired
	SubjectRepository subjectRepo;
	
	@PostMapping(value = "/so/jpa0/insertpupil")
	ResponseEntity<Void> insertPupil(){
		//insertion should also insert parts
		Subject math    = new Subject(0L, "Maths");
		
		Pupil alice = new Pupil(0, "alice", Arrays.asList(math));
		
		pupilRepo.save(alice);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	@PostMapping(value = "/so/jpa0/insertpupil2")
	ResponseEntity<Void> insertPupil2(){
		//insertion should use existing parts
		Subject math    = new Subject(0L, "Maths");
		
		Pupil alice = new Pupil(0, "alice", Arrays.asList(math));		
		pupilRepo.save(alice);
		Pupil bob   = new Pupil(1, "bob",   Arrays.asList(math));
		pupilRepo.save(bob);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/so/jpa0/insertpupils")
	ResponseEntity<Void> insertPupils(){
			
		Subject math    = new Subject(0L, "Maths");
		Subject english = new Subject(1L, "English");
		Subject spanish = new Subject(2L, "Spanish");
			
		Pupil alice = new Pupil(0, "alice", Arrays.asList(math));
		Pupil bob   = new Pupil(1, "bob",   Arrays.asList(english));
		pupilRepo.save(alice);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/so/jpa0/pupils")
	ResponseEntity<Void> deletePupils(){
		pupilRepo.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/so/jpa0/subjects")
	ResponseEntity<Void> deleteTags(){
		subjectRepo.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
			
//		//prove pr exists
//		long a = pr.count();
//
//		CategoryBE cat = new CategoryBE();
//		cat.setId(0);
//		cat.setName("c1");
//		StatusEnum se = StatusEnum.AVAILABLE;
//		List<String> urls = Arrays.asList();
//
//		TagBE[] tags = new TagBE[2];
//		tags[0] = new TagBE();
//		tags[0].setId(0);
//		tags[0].setName("tag-a");
//
//		tags[1] = new TagBE();
//		tags[1].setId(1);
//		tags[1].setName("tag-b");
//		
//		tr.save(tags[0]);
//		tr.save(tags[1]);
//		
//		PetBE pet0 = new PetBE(0L, "a", cat, urls, tags, se);
//		pr.save(pet0);
//
//		tags[0] = new TagBE();
//		tags[0].setId(2);
//		tags[0].setName("tag-c");
//
//		tags[1] = new TagBE();
//		tags[1].setId(3);
//		tags[1].setName("tag-d");
//
//		PetBE pet1 = new PetBE(1L, "b", cat, urls, tags, se);
//		pr.save(pet1);
//
//		
//		pr.save(pets.get(0));
//		pr.save(pets.get(1));
//		pr.save(pets.get(2));
//		
//		pr.saveAll(pets.subList(3, 5));
	
	
}
