package com.fabio.neostoreapi.supplier.model;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class SupplierResponse {

	private Integer id;

	private String cnpj;

	private String name;

	private String description;

	private String email;

	public SupplierResponse(Supplier supplier) {
		this.id = supplier.getId();
		this.cnpj = maskCnpj(supplier.getCnpj());
		this.name = supplier.getName();
		this.description = supplier.getDescription();
		this.email = supplier.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	private String maskCnpj(String cnpj) {
		try {
			MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(cnpj);
		} catch (ParseException e) {
			throw new RuntimeException("Erro ao formatar CNPJ", e);
		}
	}

}
