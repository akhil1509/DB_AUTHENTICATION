package com.example.CRUD.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUD.model.User;
import com.example.CRUD.service.UserServiceImpl;

@RestController
@RequestMapping("/base")
public class UserController {
	
	private UserServiceImpl userServiceImpl;

	public UserController(UserServiceImpl userServiceImpl) {
		
		this.userServiceImpl = userServiceImpl;
	}
	
	@PostMapping("/create")
	public User createUsers(@RequestBody User user) {
		return userServiceImpl.createUsers(user);
	}

	@GetMapping("getAll")
	public List<User> getAllUsers(){
		return userServiceImpl.getAllUsers();
	}
	@GetMapping("/getUser/{id}")
	public User getUserById(@PathVariable Long id) {
		 return userServiceImpl.getUserById(id);
	}
	
	@PutMapping("/update")
	public User updateUser(@RequestBody User user) {
		return userServiceImpl.updateUser(user);
	}
	@DeleteMapping("/delete/{id}")
	public String deleteUserById(@PathVariable Long id) {
		return userServiceImpl.deleteUserById(id);
	}
	
}
