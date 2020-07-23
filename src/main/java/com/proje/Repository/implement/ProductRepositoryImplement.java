package com.proje.Repository.implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.proje.DBConnection.DBConnection;
import com.proje.Repository.ProductRepository;
import com.proje.model.Brand;
import com.proje.model.Category;
import com.proje.model.Product;
import com.proje.model.User;
import com.proje.model.queries.ProductQueries;
import com.proje.model.queries.UserQuery;

public class ProductRepositoryImplement implements ProductRepository  {

	private final Logger logger = LogManager.getFormatterLogger();
	private Connection connection= null;
	private PreparedStatement preparedStatement= null;
	private ResultSet resultSet=null;
	private PreparedStatement preparedtStatement=null;
	private Brand brand;
	private List<Product> products;
	private Product product;
	
/*="INSERT INTO (productId,productName,unitPrice,avaliable,addDate,updateDate,categoryId,brandId) values(?,?,?,?,?,?,?,?)" ;*/	
	@Override
	public Product saveProduct(Product product) {
		connection = DBConnection.getconnection();

			try {
				LocalDateTime localDateTime=LocalDateTime.now();
				preparedStatement = (PreparedStatement) connection.prepareStatement(ProductQueries.saveProductQuery);
				;
				preparedStatement.setInt(1, product.getProductId());
				preparedStatement.setString(2, product.getProductName());
				preparedStatement.setDouble(3, product.getUnitPrice());
				preparedStatement.setInt(4,  product.getAvaliable());
				preparedStatement.setTimestamp(5, Timestamp.valueOf(localDateTime));
				preparedStatement.setTimestamp(6, null);
				preparedStatement.setInt(7, product.getCategory().getCategoryId());
				preparedStatement.setInt(8, product.getBrand().getBrandId());

				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				logger.warn(product.getProductId() + " ID'li User kaydedilirken hata olustu" + e);
			} finally {

				DBConnection.closeConnection(connection, preparedStatement, resultSet);
			}
			return product;
		}

		

	@Override
	public boolean saveBatchProduct(List<Product> products) {
		connection = DBConnection.getconnection();

		try {
			LocalDateTime localDateTime=LocalDateTime.now();
			preparedStatement = (PreparedStatement) connection.prepareStatement(ProductQueries.saveProductQuery);
			for(Product product:products) {
			preparedStatement.setInt(1, product.getProductId());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setDouble(3, product.getUnitPrice());
			preparedStatement.setInt(4,  product.getAvaliable());
			preparedStatement.setTimestamp(5, Timestamp.valueOf(localDateTime));
			preparedStatement.setTimestamp(6, null);
			preparedStatement.setInt(7, product.getCategory().getCategoryId());
			preparedStatement.setInt(8, product.getBrand().getBrandId());

			
			}preparedStatement.executeBatch();
		} catch (SQLException e) {
			logger.warn("ürün listesi eklenýrken bir hata var : " + e);
			return false;
		} finally {

			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}return true;
		
	}

	@Override
	public Product updateProduct(Product product) {
		connection = DBConnection.getconnection();
/*="Update product set productName=?,UnitPrice=?,Available=?, updateDate=?,categoryId=?,brandId=?*/
		try {
			LocalDateTime localDateTime=LocalDateTime.now();
			preparedStatement = (PreparedStatement) connection.prepareStatement(ProductQueries.updateProductQuery);
			
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setDouble(2, product.getUnitPrice());
			preparedStatement.setInt(3,  product.getAvaliable());
			preparedStatement.setTimestamp(4, Timestamp.valueOf(localDateTime));
		
			preparedStatement.setInt(5, product.getCategory().getCategoryId());
			preparedStatement.setInt(6, product.getBrand().getBrandId());
			preparedStatement.setInt(7, product.getProductId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.warn("ürün güncellenirken bir hata var : " + e);
			
		} finally {

			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}		return product;
	}

	@Override
	public boolean removeProduct(int id) {
/*DELETED FROM user_product where productId=?
 * "DELETE FROM product where productId"*/
		
		connection = DBConnection.getconnection();

		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(ProductQueries.deletedUser_ProductQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
			preparedStatement = (PreparedStatement) connection.prepareStatement(ProductQueries.deleteProductQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.warn(" User Silinirken hata olustu" + e);
			return false;
		} finally {

			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return true;
	}

	/*/*="INSERT INTO (productId,productName,unitPrice,available,addDate,updateDate,categoryId,brandId) values(?,?,?,?,?,?,?,?)" ;*/	

	@Override
	public Product findProductById(int id) {
		connection = DBConnection.getconnection();
		Product product = null;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(ProductQueries.findProductByIdQuery);
			preparedStatement.setInt(1, id);
			resultSet =  preparedStatement.executeQuery();

			if (resultSet.next()) {
				int productId = resultSet.getInt("productId");
				String productName = resultSet.getString("productName");
				Double unitPrice = resultSet.getDouble("unitPrice");
				int avaliable=resultSet.getInt("avaliable") ;
				Date addDate = resultSet.getDate("birthoDate");
				Date updateDate = resultSet.getDate("updateDate");
				
				int categoryId =resultSet.getInt("categoryId");
				String categoryName = resultSet.getString("categoryName");

				int brandId =resultSet.getInt("brandId");

				String brandName = resultSet.getString("brandName");
				Category category=new Category (categoryId,categoryName);
				Brand brand =new Brand(brandId,brandName);
				product = new Product(productId,productName,unitPrice,avaliable,addDate,updateDate,category,brand);
				products.add(product);
			}
		} catch (SQLException e) {
			logger.warn(id + "li ürün  aranýrken hata olustu" + e);
		} finally {

			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return product;
	}		
	@Override
	public List<Product> findProducts() {
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(ProductQueries.findProductsQuery);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				
				int productId = resultSet.getInt("productId");
				String productName = resultSet.getString("productName");
				Double unitPrice = resultSet.getDouble("unitPrice");
				int avaliable=resultSet.getInt("avaliable") ;
				Date addDate = resultSet.getDate("birthoDate");
				Date updateDate = resultSet.getDate("updateDate");
				
				int categoryId =resultSet.getInt("categoryId");
				String categoryName = resultSet.getString("categoryName");

				int brandId =resultSet.getInt("brandId");

				String brandName = resultSet.getString("brandName");
				Category category=new Category (categoryId,categoryName);
				Brand brand =new Brand(brandId,brandName);
				product = new Product(productId,productName,unitPrice,avaliable,addDate,updateDate,category,brand);
				products.add(product);
				
			}
		} catch (SQLException e) {

			
			logger.warn(" ürünler  listelenirken hata olustu " + e);

		}finally {

			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}

		
		return products;
	}

}
