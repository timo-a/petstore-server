package com.example.petstore.stacko.jpa0;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject {

	@Id
	private long id;
	
	@NonNull
	private String name;
	
	@ManyToMany
	private List<Subject> subjects;
	
	public Subject(long id, String name) {
		this.id   = id;
		this.name = name;
	}
	
}
