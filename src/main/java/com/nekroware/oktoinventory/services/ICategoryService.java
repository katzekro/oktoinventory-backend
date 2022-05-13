package com.nekroware.oktoinventory.services;

import org.springframework.http.ResponseEntity;

import com.nekroware.oktoinventory.model.Category;
import com.nekroware.oktoinventory.response.CategoryResponseRest;

public interface ICategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();
	public ResponseEntity<CategoryResponseRest> searchById(Long id);
	public ResponseEntity<CategoryResponseRest> save(Category category);
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id);
	
}
