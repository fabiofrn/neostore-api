package com.fabio.neostoreapi.supplier.model;

import com.fabio.neostoreapi.generic.model.EntityBase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "cnpj" }) })
public class Supplier extends EntityBase {

	@Column(length = 14, nullable = false, unique = true)
	private String cnpj;

	@Column(length = 80, nullable = false)
	private String name;

	@Column(length = 200)
	private String description;

	@Column(length = 60, nullable = false)
	private String email;

	public Supplier() {
	}

	public Supplier(String cnpj, String name, String description, String email) {
		this.cnpj = cnpj;
		this.name = name;
		this.description = description;
		this.email = email;
	}

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
