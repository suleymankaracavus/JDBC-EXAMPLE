package com.proje.model;

import java.util.Date;

public class Product {
	private int productId;
	private String productName;
	private double unitPrice;
	private int avaliable;
	private Date addDate;
	private Date UpdateDate;
	private Category category;
	private Brand brand;
	
	public Product() {
		
		
	}

	public Product(int productId, String productName, double unitPrice, int avaliable, Date addDate, Date updateDate,
			Category category, Brand brand) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.avaliable = avaliable;
		this.addDate = addDate;
		UpdateDate = updateDate;
		this.category = category;
		this.brand = brand;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getAvaliable() {
		return avaliable;
	}

	public void setAvaliable(int avaliable) {
		this.avaliable = avaliable;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Date getUpdateDate() {
		return UpdateDate;
	}

	public void setUpdateDate(Date updateDate) {
		UpdateDate = updateDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", unitPrice=" + unitPrice
				+ ", avaliable=" + avaliable + ", addDate=" + addDate + ", UpdateDate=" + UpdateDate + ", category="
				+ category + ", brand=" + brand + "]";
	}
	
	

}
