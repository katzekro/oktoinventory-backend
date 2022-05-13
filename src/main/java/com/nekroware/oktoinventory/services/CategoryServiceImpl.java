package com.nekroware.oktoinventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nekroware.oktoinventory.dao.ICategoryDao;
import com.nekroware.oktoinventory.model.Category;
import com.nekroware.oktoinventory.response.CategoryResponseRest;

@Service
public class CategoryServiceImpl implements ICategoryService{
	
	@Autowired
	private ICategoryDao categoryDao;
	
	@Override
	@Transactional( readOnly = true )
	public ResponseEntity<CategoryResponseRest> search() {
		
		CategoryResponseRest response = new CategoryResponseRest();
		try {
			List<Category> category = (List<Category>) categoryDao.findAll();
			response.getCategoryResponse().setCategory(category);
			response.setMetadata("Respuesta OK", "00", "Respuesta Existosa");
			
		} catch (Exception e) {
			response.setMetadata("Fail ", "-1", "Error al Realizar la Consulta");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchById(Long id) {
		
		 CategoryResponseRest response = new CategoryResponseRest();
		 List<Category> list = new ArrayList<>();
		
		 try {
		
			Optional<Category> category = categoryDao.findById(id); 
			
			if(category.isPresent()) {
				list.add(category.get());
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("OK ", "00", "Categoria Encontrada");
			} else {
				response.setMetadata("Fail ", "-1", "No se encontraron categorias");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			 
		} catch (Exception e) {
			
			response.setMetadata("Fail ", "-1", "Error al Realizar la Consulta por ID");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		 
		return null;
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> save(Category category) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			
			Category categorySaved = categoryDao.save(category);
			
			if(categorySaved != null) {
				list.add(categorySaved);
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("OK ", "00", "Categoria guardada");
			} else {
				response.setMetadata("Fail ", "-1", "Error al guardar categoria");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			response.setMetadata("Fail ", "-1", "Error al guardar categoria");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			
			Optional<Category> categorySearch = categoryDao.findById(id);
			if(categorySearch.isPresent()) {
				// Actualizacion del registro que previamente busco
				categorySearch.get().setName(category.getName());
				categorySearch.get().setDescription(category.getDescription());
				
				Category categoryToUpdate = categoryDao.save(categorySearch.get()); 
				if(categoryToUpdate != null) {
					list.add(categoryToUpdate);
					response.getCategoryResponse().setCategory(list);
					response.setMetadata("OK", "00", "Categoria Actualizada Correctamente");
				} else {
					response.setMetadata("Fail ", "-1", "Ocurrio un error al intentar actualizar categoria");
					return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				response.setMetadata("Fail ", "-1", "Error - ID de categoria no encontrado");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
			
		} catch (Exception e) {
			response.setMetadata("Fail ", "-1", "Error al actualizar categoria");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> deleteCategoryById(Long id) {
		CategoryResponseRest response = new CategoryResponseRest();
		
		try {
			
			categoryDao.deleteById(id);
			response.setMetadata("OK", "00", "Categoria Eliminada.");
			
			
		} catch (Exception e) {
			response.setMetadata("Fail ", "-1", "Ocurrio un error al intentar eliminar la categoria seleccionada");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

}
