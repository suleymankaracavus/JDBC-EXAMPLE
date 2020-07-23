package com.proje.model.queries;

public class ProductQueries {

	public static final String  saveProductQuery="INSERT INTO (productId,productName,unitPrice,avaliable,addDate,updateDate,categoryId,brandId) values(?,?,?,?,?,?,?,?)" ; 
	
		public static final String updateProductQuery="Update product set productName=?,UnitPrice=?,avaliable=?,updateDate=?,categoryId=?,brandId=? where productId=? ";
		
		public static final String deletedUser_ProductQuery="DELETED FROM user_product where productId=?";
		public static final String deleteProductQuery="DELETE FROM product where productId";
		public static final String findProductByIdQuery="Select*from product p Left Join category c on(p.categoryId=c.categoryId) LEFT Joýn brand b on(p.brandId=b.brandId) where productId=?  ";
		public static final String findProductsQuery="Select*from product p Left Join category c on(p.categoryId=c.categoryId) LEFT Joýn brand b on(p.brandId=b.brandId) ";

	}


