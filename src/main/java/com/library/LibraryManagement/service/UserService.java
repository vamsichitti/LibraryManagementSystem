package com.library.LibraryManagement.service;

import java.util.List;

import com.library.LibraryManagement.entity.User;
import com.library.LibraryManagement.exceptions.ResourceAlreadyExistsException;
import com.library.LibraryManagement.exceptions.ResourceNotFoundException;

public interface UserService {
	public User createUser(User user) throws ResourceAlreadyExistsException;
	 public User updateUser(User user);
	 public List<User> getAllUsers();
	 public User getUserById(Long id) throws ResourceNotFoundException;
	void deleteUserById(Long id) throws ResourceNotFoundException;
}
