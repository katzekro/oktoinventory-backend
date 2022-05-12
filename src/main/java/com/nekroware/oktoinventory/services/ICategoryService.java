package com.nekroware.oktoinventory.services;

import org.springframework.http.ResponseEntity;

import com.nekroware.oktoinventory.response.CategoryResponseRest;

public interface ICategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();
	
	public ResponseEntity<CategoryResponseRest> searchById(Long id);
	
	
}
