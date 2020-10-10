package com.example.petstore.stacko.jpa0;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor //otherwise there are errors on delete
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "pupils")
public class Pupil {
	
	@Id
	private long id;

	@NonNull
	private String name;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
		name = "pupils_subjects", 
		joinColumns = @JoinColumn(name = "pupil_id"), 
		inverseJoinColumns = @JoinColumn(name = "subject_id"))
	@OrderColumn(name = "id")
	private List<Subject> subjects;
	
	public Pupil(String name, List<Subject> subjects) {
		this.name = name;
		this.subjects = subjects;
	}
	
}
