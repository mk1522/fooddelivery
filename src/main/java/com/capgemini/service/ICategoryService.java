package com.capgemini.service;

import java.util.List;
import com.capgemini.entities.Category;

public interface ICategoryService {
	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public Category removeCategory(Category category);
	
	public Category viewCategory(Category category);
	
	public List<Category> viewAllCategory();
}
