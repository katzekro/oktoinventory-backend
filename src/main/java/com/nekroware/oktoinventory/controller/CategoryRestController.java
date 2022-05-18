package com.nekroware.oktoinventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nekroware.oktoinventory.model.Category;
import com.nekroware.oktoinventory.response.CategoryResponseRest;
import com.nekroware.oktoinventory.services.ICategoryService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
	
	@Autowired
	private ICategoryService catService;
	
	/*
	 * Get All Categories 
	 * @return
	 * */
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> searchCategories() {
		ResponseEntity<CategoryResponseRest> response = catService.search();
		return response;
	}
	
	/*
	 * Get Categories using id 
	 * @param id
	 * @return
	 * */
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id) {
		ResponseEntity<CategoryResponseRest> response = catService.searchById(id);
		return response;
	} 
	
	/**
	 * Save New Category
	 * @param category
	 * @return
	 */
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseRest> saveNewCategory(@RequestBody Category category){
		ResponseEntity<CategoryResponseRest> response = catService.save(category);
		return response;
	}
	
	/**
	 * Update Categories
	 * @param category
	 * @param id
	 * @return
	 */
	@PutMapping("/categories/{id}")
	public  ResponseEntity<CategoryResponseRest> updateCategory(@RequestBody Category category, @PathVariable Long id){
		ResponseEntity<CategoryResponseRest> response = catService.update(category, id);
		return response;
	}
	
	/**
	 * Delete Category By Id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> deleteCategoryById(@PathVariable Long id){
		ResponseEntity<CategoryResponseRest> response = catService.deleteCategoryById(id);
		return response;
	}
	
}
