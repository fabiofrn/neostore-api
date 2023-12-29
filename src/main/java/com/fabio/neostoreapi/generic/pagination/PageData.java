package com.fabio.neostoreapi.generic.pagination;

import java.util.List;

public class PageData<T> {
	
	private List<T> records;
    
	private long totalRecords;
    
	private int totalPages;
    
	private int pageSize;

	public PageData(List<T> records, long totalRecords, int pageSize) {
		this.records = records;
		this.totalRecords = totalRecords;
		this.totalPages = (int) Math.ceil(totalRecords / pageSize);
		this.pageSize = pageSize;
	}

	public List<T> getRecords() {
		return records;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}
	
	
	
	

}
