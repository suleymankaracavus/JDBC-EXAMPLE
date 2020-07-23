package com.proje.Repository;

import java.util.List;

import com.proje.model.Category;

public interface CategoryRepository {
	Category findCategoryById(int id);
	List<Category> findCategories();
	Category findCategory(int id);
	

	

}
