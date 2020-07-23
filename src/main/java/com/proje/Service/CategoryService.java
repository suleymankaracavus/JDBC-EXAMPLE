package com.proje.Service;

import java.util.List;

import com.proje.model.Category;

public interface CategoryService {
	
	
	Category findCategoryById(int id);
	List<Category> findCategories();
	Category findCategory(int id);

}
