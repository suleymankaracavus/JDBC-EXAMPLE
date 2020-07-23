package com.proje.Repository.implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.proje.DBConnection.DBConnection;
import com.proje.Repository.UserRepository;
import com.proje.model.Brand;
import com.proje.model.Category;
import com.proje.model.Product;
import com.proje.model.User;
import com.proje.model.queries.UserQuery;

public class UserRepositoryImplement implements UserRepository {

	private final Logger logger = LogManager.getFormatterLogger();
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private PreparedStatement preparedtStatement;
	
	@Override
	public User saveUser(User user) {
		connection = DBConnection.getconnection();
//	"INSERT INTO user(userId,firstName,lastName,birthodate,username) values(?,?,?,?,?,)";

		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(UserQuery.saveUserQuery);
			;
			preparedStatement.setInt(1, user.getUserId());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastname());
			preparedStatement.setDate(4, user.getBirthofDate());
			preparedStatement.setString(5, user.getUsername());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.warn(user.getUserId() + " ID'li User kaydedilirken hata olustu" + e);
		} finally {

			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return user;
	}

	@Override
	public boolean saveUserProduct(int userId, int productId) {
		connection = DBConnection.getconnection();
// user_product(userId,productId) values(?,?)
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(UserQuery.saveUser_ProductQuery);
			;
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, productId);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.warn(" User_Product  kaydedilirken hata olustu" + e);
		} finally {

			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return true;
	}

//"Update user set firstName=?,lastName=?,birthoDate=?,username where userId=? "
	@Override
	public User updateUser(User user) {
		connection = DBConnection.getconnection();
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(UserQuery.updateUserQuery);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastname());
			preparedStatement.setDate(3, (Date) user.getBirthofDate());
			preparedStatement.setString(4, user.getUsername());
			preparedStatement.setInt(5, user.getUserId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.warn(" User güncellenirken hata olustu" + e);
		} finally {

			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return user;
	}

//	 = "DELETED FROM user_product where userId=?";
// = "DELETE FROM user where userId=?";
	@Override
	public boolean removeUser(int id) {
		connection = DBConnection.getconnection();

		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(UserQuery.deletedUser_ProductByIdQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			preparedStatement = (PreparedStatement) connection.prepareStatement(UserQuery.deleteUserByIdQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.warn(" User Silinirken hata olustu" + e);
		} finally {

			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return false;
	}

//	 findUserById = "Select*from user where userId=? ";

	@Override
	public User findUserById(int id) {
		connection = DBConnection.getconnection();
		User user = null;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(UserQuery.findUserByIdQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int userId = resultSet.getInt("userId");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				Date birthofDate = resultSet.getDate("birthofDate");
				String username = resultSet.getString("username");
				user=new User(userId, firstName, lastName, birthofDate, username);
			}
		} catch (SQLException e) {
			logger.warn(" User Bulunurken hata olustu" + e);
		} finally {

			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return user;
	}

	// "SELECT*FROM USER u left outer join user_product up ON(u.userId =up.userId)"
	// + "Left join product p ON(up.productId=p.productId)"
	// + "Left Join category c on (p.categoryId)"
	// + "Left join brand b on(p.brandId=b.brandId)"
	// + "Where u.user=101)";

	@Override
	public User findUserProductById(int id) {
		connection = DBConnection.getconnection();
		User user = null;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(UserQuery.findUserProductQuery);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			boolean durum = true;

			
		List<Product> products= new ArrayList<Product>();
			while (resultSet.next()) {

				if (durum) {
					int userId = resultSet.getInt("userId");
					String firstName = resultSet.getString("firstName");
					String lastName = resultSet.getString("lastName");
					Date birthoDate = resultSet.getDate("birthoDate");
					String username = resultSet.getString("username");
					user = new User(userId, firstName, lastName, birthoDate, username);
					durum = false;
				}
				int productId = resultSet.getInt("productId");
				String productName = resultSet.getString("productName");
				double unitPrice = resultSet.getDouble("unitPrice");
				int avaliable = resultSet.getInt("avaliable");
				Date addDate = resultSet.getDate("addDate");
				Date updateDate = resultSet.getDate("updateDate");
				
				int categoryId =resultSet.getInt("categoryId");
				String categoryName =resultSet.getString("categoryName");

				int brandId =resultSet.getInt("brandyId");
				String brandName=resultSet.getString("brandName");
				Category category = new Category(categoryId,categoryName);
				Brand brand=new Brand(brandId,brandName);
				Product product=new Product(productId, productName, unitPrice, avaliable, addDate, updateDate, category, brand);
				products.add(product);
				
				
			}user.setProducts(products);

		}

		catch (SQLException e) {
			logger.warn("User ve ürünleri bulunurken hata meydana geldi");
		}finally {

			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return user;
	}


		

	@Override
	public List<User> findUsers() {
		connection = DBConnection.getconnection();
		List<User> users=new ArrayList<>();

try {
	preparedStatement = (PreparedStatement) connection.prepareStatement(UserQuery.findUsersQuery);
	resultSet=preparedStatement.executeQuery();

	while (resultSet.next()) {

		int userId = resultSet.getInt("userId");
		String firstName = resultSet.getString("firstName");
		String lastName = resultSet.getString("lastName");
		Date birthofDate = resultSet.getDate("birthofDate");
		String username = resultSet.getString("username");
		User user= new User(userId, firstName, lastName, birthofDate, username);
		users.add(user);

	}
	
} catch (SQLException e) {
	logger.warn("User  listesi alýnýrken hata meydana geldi" + e);

}finally {

	DBConnection.closeConnection(connection, preparedStatement, resultSet);
}
		return users;
	
	}

}
