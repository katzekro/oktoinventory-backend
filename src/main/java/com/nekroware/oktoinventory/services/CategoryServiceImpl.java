package com.nekroware.oktoinventory.services;

import java.util.List;

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

}
