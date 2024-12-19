package com.btl.qltv.btljava.phantrang;

import java.util.List;

import org.springframework.data.domain.Page;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PagedRespone<T> {
	private List<T> items;
    private int totalItems;
    private int totalPages;
    private int currentPage;
    
	public PagedRespone() {
	}
	
	public PagedRespone(Page<T> page) {
		this.items = page.getContent();
		this.totalItems = (int) page.getTotalElements();
		this.totalPages = page.getTotalPages();
		this.currentPage = page.getNumber();
	}
	@JsonCreator
	public PagedRespone( 
			@JsonProperty("content") List<T> items,
	        @JsonProperty("totalPages") int totalPages,
	        @JsonProperty("currentPage") int currentPage,
	        @JsonProperty("size") int totalItems) {
		
		this.items = items;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.currentPage =  currentPage;
	}
	
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
