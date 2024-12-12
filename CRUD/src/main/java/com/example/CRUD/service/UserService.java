package com.example.CRUD.service;

import java.util.List;

import com.example.CRUD.model.User;

public interface UserService {
	
	public User createUsers(User user);
	
	public List<User> getAllUsers();
	
	public User getUserById(Long id);
	
	public User updateUser( User user);
	
	public String deleteUserById(Long id);

}
