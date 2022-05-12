package com.nekroware.oktoinventory.response;

import java.util.List;

import com.nekroware.oktoinventory.model.Category;

import lombok.Data;

@Data
public class CategoryResponse {
	private List<Category> category; 
}
