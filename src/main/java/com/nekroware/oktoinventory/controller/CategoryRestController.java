package com.nekroware.oktoinventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nekroware.oktoinventory.response.CategoryResponseRest;
import com.nekroware.oktoinventory.services.ICategoryService;


@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
	
	@Autowired
	private ICategoryService catService;
	
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> searchCategories() {
		ResponseEntity<CategoryResponseRest> response = catService.search();
		return response;
	}  
	
}
