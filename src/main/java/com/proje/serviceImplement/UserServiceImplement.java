package com.proje.serviceImplement;

import java.util.List;

import com.proje.Repository.UserRepository;
import com.proje.Repository.implement.UserRepositoryImplement;
import com.proje.Service.UserService;
import com.proje.model.User;

public class UserServiceImplement implements UserService  {

	private UserRepository userRepository=new UserRepositoryImplement();
	
	@Override
	public User saveUser(User user) {
		
		
		
		return  userRepository.saveUser(user);
	}

	@Override
	public boolean saveUserProduct(int userId, int productId) {
		return userRepository.saveUserProduct(userId, productId);
	}

	@Override
	public User updateUser(User user) {
		
		return userRepository.updateUser(user);
	}

	@Override
	public boolean removeUser(int id) {
		return userRepository.removeUser(id);
	}

	@Override
	public User findUserById(int id) {
		return userRepository.findUserById(id);
	}

	@Override
	public User findUserProductById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findUserProductById(id);
	}
	
	

	@Override
	public List<User> findUsers() {
		// TODO Auto-generated method stub
		return userRepository.findUsers();
	}
	
	
}
