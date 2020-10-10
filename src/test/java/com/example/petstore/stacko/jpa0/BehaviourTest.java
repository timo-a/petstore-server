package com.example.petstore.stacko.jpa0;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BehaviourTest {

	@Autowired
	PupilRepository pupilRepo;
	
	@Autowired
	SubjectRepository subjectRepo;

	@BeforeEach
	public void setUp() {
		pupilRepo.deleteAll();
		subjectRepo.deleteAll();
		
	}
	
	@Test
	public void testAddPupil() {

		Subject math = new Subject(0L, "Maths");
		
		Pupil alice = new Pupil(0, "alice", Arrays.asList(math));
		
		pupilRepo.save(alice);

	}
	
	@Test
	public void testAddPupilWithExistingRef() {

		Subject math = new Subject(0, "Maths");
		
		Pupil alice = new Pupil(0, "alice", Arrays.asList(math));
		Pupil bob   = new Pupil(1, "bob",   Arrays.asList(math));
		pupilRepo.save(alice);
		pupilRepo.save(bob);
		assertThat(pupilRepo.count()).isEqualTo(2);
		assertThat(subjectRepo.count()).isEqualTo(1);

	}
	
	@Test
	public void testAddSubjectThenPupil() {

		Subject math = new Subject(0L, "Maths");
		subjectRepo.save(math);
		Pupil alice = new Pupil(0, "alice", Arrays.asList(math));
		pupilRepo.save(alice);
		assertThat(pupilRepo.count()).isEqualTo(1);
		assertThat(subjectRepo.count()).isEqualTo(1);

	}
	
	@Test
	public void testAddTwoSubjectsThenPupil() {

		Subject math    = new Subject(0, "Maths");
		Subject english = new Subject(1, "English");
		subjectRepo.save(math);
		subjectRepo.save(english);

		math    = new Subject(0, "Maths");
		english = new Subject(1, "English");

		Pupil alice = new Pupil(0, "alice", Arrays.asList(math, english));
		pupilRepo.save(alice);
		assertThat(pupilRepo.count()).isEqualTo(1);
		assertThat(subjectRepo.count()).isEqualTo(2);

	}

	private void a() {
		
	}
	
	@Test
	public void testAddTwoSubjectsThenPupilNoID() {

		Subject math    = new Subject("Maths");
		Subject english = new Subject("English");
		subjectRepo.save(math);
		subjectRepo.save(english);
//		Pupil alice = new Pupil("alice", Arrays.asList(math, english));
//		pupilRepo.save(alice);
//		assertThat(pupilRepo.count()).isEqualTo(1);
//		assertThat(subjectRepo.count()).isEqualTo(2);

	}
	
}
