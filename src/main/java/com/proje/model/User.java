package com.proje.model;

import java.sql.Date;
import java.util.List;

public class User {

	private int userId;
	private String firstName;
	private String lastname;
	private Date birthofDate;

	private String username;
	private List<Product> products;

	public User() {

	}

	public User(int userId, String firstName, String lastName, Date birthofDate, String username) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastname = lastname;
		this.birthofDate = birthofDate;
		this.username = username;
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthofDate() {
		return birthofDate;
	}

	public void setBirthofDate(Date birthofDate) {
		this.birthofDate = birthofDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastname=" + lastname + ", birthDate="
				+ birthofDate + ", username=" + username + ", products=" + products + "]";
	}

}
