package com.proje.serviceImplement;

import java.util.List;

import com.proje.Repository.CategoryRepository;
import com.proje.Repository.implement.CategoryRepositoryImplement;
import com.proje.Service.CategoryService;
import com.proje.model.Category;

public class CategoryServiceImplement implements CategoryService {

	private CategoryRepository categoryRepository = new CategoryRepositoryImplement();

	@Override
	public List<Category> findCategories() {
		return categoryRepository.findCategories();
	}

	@Override
	public Category findCategoryById(int id) {
		return categoryRepository.findCategoryById(id);
	}

	@Override
	public Category findCategory(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
