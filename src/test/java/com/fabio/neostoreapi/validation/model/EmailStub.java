package com.fabio.neostoreapi.validation.model;

import jakarta.validation.constraints.Email;

public class EmailStub {
	
	@Email
	private String email;

	public EmailStub(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

}
