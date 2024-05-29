package com.library.LibraryManagement.service;

import java.util.List;

import com.library.LibraryManagement.entity.User;

public interface UserService {
	public User createUser(User user);
	 public User updateUser(User user);
	 public List<User> getAllUsers();
	 public User getUserById(int id);
	 public void deleteUserById(int id);
}
