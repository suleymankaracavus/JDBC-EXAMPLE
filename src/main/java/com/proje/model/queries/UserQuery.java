package com.proje.model.queries;

public class UserQuery {

	public static final String saveUserQuery = "INSERT INTO user(userId,firstName,lastName,birthodDate,username) values(?,?,?,?,?)";
	public static final String saveUser_ProductQuery = "INSERT INTO user_product(userId,productId) values(?,?)";

	public static final String updateUserQuery = "Update user set firstName=?,lastName=?,birthoDate=?,username=? where userId=? ";

	public static final String deletedUser_ProductByIdQuery = "DELETED FROM user_product where userId=?";
	public static final String deleteUserByIdQuery = "DELETE FROM user where userId=?";
	public static final String findUserByIdQuery = "Select*from user where userId=? ";
	public static final String findUsersQuery = "SELECT*from proje2.user";
	public static final String findUserProductQuery="SELECT*FROM USER u left outer join user_product up ON(u.userId =up.userId)"
			+ "Left join product p ON(up.productId=p.productId)"
			+ "Left Join category c on (p.categoryId)"
			+ "Left join brand b on(p.brandId=b.brandId)"
			+ "Where u.userId=?)";
	
	
}
