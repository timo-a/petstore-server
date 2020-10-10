package com.example.petstore.backend.auth;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "usercredentials")
public class UserAuthBE {

	@Id
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String encodedPassword;

}
