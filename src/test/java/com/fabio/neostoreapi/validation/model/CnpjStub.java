package com.fabio.neostoreapi.validation.model;

import com.fabio.neostoreapi.validation.Cnpj;

public class CnpjStub {
	
	@Cnpj
	private String cnpj;

	public CnpjStub(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getCnpj() {
		return cnpj;
	}

}
