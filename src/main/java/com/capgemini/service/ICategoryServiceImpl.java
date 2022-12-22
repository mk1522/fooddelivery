package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Category;
import com.capgemini.repo.ICategoryRepository;

@Service
public class ICategoryServiceImpl implements ICategoryService {

	@Autowired
	ICategoryRepository repo;

	@Override
	public Category addCategory(Category category) {
		return repo.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		Category updateCat = null;
		if (repo.existsById(category.getCatId()))
			updateCat = repo.save(category);
		return updateCat;
	}

	@Override
	public Category removeCategory(Category category) {
		Category deleteCat = category;
		if (repo.existsById(category.getCatId())) {
			repo.delete(category);
			return deleteCat;
		}
		return null;
	}

	@Override
	public Category viewCategory(Category category) {
		return repo.findById(category.getCatId()).get();
	}

	@Override
	public List<Category> viewAllCategory() {
		return repo.findAll();
	}

}
