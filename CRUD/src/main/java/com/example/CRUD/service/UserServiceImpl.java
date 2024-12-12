package com.example.CRUD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CRUD.model.User;
import com.example.CRUD.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	//@Autowired
	public UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}

	@Override
	public User createUsers(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		
		 User getuserById = userRepository.findById(id).orElseThrow(()-> new RuntimeException("data not dound"));
		 return getuserById;
		 
	}

	@Override
	public User updateUser( User user) {
		User updateUser = userRepository.findById(user.getId()).get();
		updateUser.setName(user.getName());
		updateUser.setAddress(user.getAddress());
		updateUser.setEmail(user.getEmail());
		updateUser.setNumber(user.getNumber());
		User update = userRepository.save(updateUser);
		return update;
		
		
	}

	@Override
	public String deleteUserById(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
			return "success";
		}
		return "not available";
	}

}
