package com.proje.Repository.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.proje.DBConnection.DBConnection;
import com.proje.Repository.CategoryRepository;
import com.proje.model.Brand;
import com.proje.model.Category;
import com.proje.model.queries.BrandQuery;
import com.proje.model.queries.CategoryQuery;

public class CategoryRepositoryImplement implements CategoryRepository {
	private final Logger logger = LogManager.getFormatterLogger();
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;


	@Override
	public Category findCategoryById(int id) {
		connection = DBConnection.getconnection();
		Category category=null;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(CategoryQuery.findCategoryByIdQuery);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				
				
				int categoryId =resultSet.getInt("categoryId");
				String categoryName=resultSet.getString("categoryName");
				category =new Category(categoryId,categoryName);
				
			}
			
		} catch (SQLException e) {
			logger.warn("kategori aranýrken hata meydana geldi"+ e );
		}
		
		return category;
	}

	@Override
	public List<Category> findCategories() {
		connection = DBConnection.getconnection();
		List<Category> categories = new ArrayList<>();
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(CategoryQuery.findCategoriesQuery);
		
		resultSet=preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			int categoryId= resultSet.getInt("categoryId");
			String categoryName=resultSet.getString("categoryName");
			Category category= new Category (categoryId,categoryName);
			
			categories.add(category);
		}
			
		} catch (SQLException e) {
			logger.warn("categori listesi alýnýrken bir hata oluþtu  " + e);		}
		
		return categories;
	}

	@Override
	public Category findCategory(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
