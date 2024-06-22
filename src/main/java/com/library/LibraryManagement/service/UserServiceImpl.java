package com.library.LibraryManagement.service;

import java.util.List;
import java.util.Optional;

import com.library.LibraryManagement.exceptions.ResourceAlreadyExistsException;
import com.library.LibraryManagement.exceptions.ResourceNotFoundException;
import com.library.LibraryManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.LibraryManagement.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public User createUser(User user) throws ResourceAlreadyExistsException {
		Optional<User> createdUser =userRepository.findById(user.getId());
		if(!createdUser.isPresent()){
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			return userRepository.save(user);
		}
		else {
			throw new ResourceAlreadyExistsException("User with ID "+user.getId()+" with name "+user.getFirstName()+" is already exists");
		}
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		List<User> userList = userRepository.findAll();
		if(userList.isEmpty()){
			throw new Exception("No users Found ");
		}
		return userList;
	}

	@Override
	public User getUserById(Long id) throws ResourceNotFoundException {

			User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User with ID "+id+" not found"));
			return user;

	}

	@Override
	public void deleteUserById(Long id) throws ResourceNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()){
			userRepository.deleteById(id);
		}
		else{
			throw new ResourceNotFoundException("User with Id "+user.get().getId()+" with name "+user.get().getFirstName()+" not Found");
		}

	}

}
