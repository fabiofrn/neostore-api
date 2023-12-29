package com.fabio.neostoreapi.supplier.model;

import com.fabio.neostoreapi.validation.Cnpj;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SupplierRequest {
	
	@Cnpj(message = "Campo cnpj não deve ser nulo")
	@Size(max = 14)
	private String cnpj;
	
	@NotNull(message = "Campo nome não deve ser nulo")
	private String name;
	
	private String description;
	
	@NotNull(message = "Campo email não deve ser nulo")
	@Email
	private String email;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
