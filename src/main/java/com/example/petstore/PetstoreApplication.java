package com.example.petstore;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.SessionScope;

import com.example.petstore.backend.db.PetBE;
import com.example.petstore.backend.db.PetRepository;
import com.example.petstore.mockups.Utils;

//TODO: springdoc-openapi for doc (but not needed) | maybe the inplemented ones
//TODO DB initialization from csv only as a branch
//TODO: frontend

/*
 * https://petstore3.swagger.io/
 * https://github.com/swagger-api/swagger-petstore
 */

@SpringBootApplication
public class PetstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetstoreApplication.class, args);
	}
	
	@Autowired
	PetRepository pr;

//	@PostConstruct
//	public void setupTables() {
//		List<PetBE> pets = Utils.makePets();
//		pr.saveAll(pets);
//	}
	
	@Bean
	@SessionScope
	public Session getSession() {
		return new Session();
	}

}
