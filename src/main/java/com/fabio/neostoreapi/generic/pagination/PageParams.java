package com.fabio.neostoreapi.generic.pagination;

import jakarta.validation.constraints.NotNull;

public class PageParams {
	
	@NotNull
	private int pageNumber;
	
	@NotNull
	private int pageSize;
	
	public PageParams() {
	}

	public PageParams(int pageNumber, int pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
}
