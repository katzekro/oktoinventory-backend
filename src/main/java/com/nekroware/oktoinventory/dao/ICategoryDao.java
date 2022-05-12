package com.nekroware.oktoinventory.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nekroware.oktoinventory.model.Category;

public interface ICategoryDao extends CrudRepository<Category, Long>{

	List<Category> findAllById(Long id);
	
	

}
