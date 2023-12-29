package com.fabio.neostoreapi.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fabio.neostoreapi.validation.model.CnpjStub;
import com.fabio.neostoreapi.validation.model.EmailStub;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

class ValidationTest {

	private static Validator validator;

	@BeforeAll
	static void init() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	void cnpjTest() {
		assertTrue(validator.validate(new CnpjStub("22842432000182")).isEmpty());
	}

	@Test
	void cnpjInvalidTest() {
		assertFalse(validator.validate(new CnpjStub("12345678901234")).isEmpty());
	}
	
	@Test
	void emailTest() {
		assertTrue(validator.validate(new EmailStub("fabio.frn81@gmail.com")).isEmpty());
	}

	@Test
	void emailInvalidTest() {
		assertFalse(validator.validate(new EmailStub("fabio.frn81.gmail")).isEmpty());
	}

}
