package com.example.petstore;

import lombok.Getter;
import lombok.Setter;

public class Session {

	@Getter
	private Boolean loggedIn;
	
	public void setLoggedIn() {
		loggedIn = true;
	}
	
	public void setLoggedOut() {
		loggedIn = false;
	}
}
