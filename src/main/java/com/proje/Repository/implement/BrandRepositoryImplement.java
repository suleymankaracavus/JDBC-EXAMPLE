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
import com.proje.Repository.BrandRepository;
import com.proje.model.Brand;
import com.proje.model.queries.BrandQuery;

public class BrandRepositoryImplement implements BrandRepository {

	private final Logger logger = LogManager.getFormatterLogger();
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedtStatement = null;


	@Override
	public Brand findBrandById(int id) {
		connection = DBConnection.getconnection();
		Brand brand = null;

		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(BrandQuery.findBrandByIdQueryId);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int brandId = resultSet.getInt("brandId");
				String brandName = resultSet.getString("brandName");
				brand = new Brand(brandId, brandName);

			}

		} catch (SQLException e) {
			logger.warn("Brand aranýrken bir hata meydana geldi" + e);
		} finally {

			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return brand;
	}

	@Override
	public Brand saveBrand(Brand brand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Brand> findBrands() {
		connection = DBConnection.getconnection();
		List<Brand> brands = new ArrayList<>();

		try {			
			preparedStatement = (PreparedStatement) connection.prepareStatement(BrandQuery.findBrands);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int brandId= resultSet.getInt("brandId");
				String brandName=resultSet.getString("brandName");
				Brand brand= new Brand (brandId,brandName);
				brands.add(brand);
				
				
			}
		} catch (SQLException e) {
logger.warn("brand listesi alýnýrken bir hata oluþtu  " + e);		
}
		finally {

			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return brands;
	}

}
