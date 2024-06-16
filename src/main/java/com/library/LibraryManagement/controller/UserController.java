package com.library.LibraryManagement.controller;

import com.library.LibraryManagement.dto.AuthRequestDTO;
import com.library.LibraryManagement.dto.JwtResponseDTO;
import com.library.LibraryManagement.entity.User;
import com.library.LibraryManagement.exceptions.ResourceAlreadyExistsException;
import com.library.LibraryManagement.exceptions.ResourceNotFoundException;
import com.library.LibraryManagement.repository.UserInterfaceRepository;
import com.library.LibraryManagement.repository.UserRepository;
import com.library.LibraryManagement.service.UserService;
import com.library.LibraryManagement.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/api/v1/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            return  new JwtResponseDTO(jwtUtil.GenerateToken(authRequestDTO.getUsername()));
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) throws ResourceAlreadyExistsException {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/getuser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) throws ResourceNotFoundException {
        User user=userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) throws ResourceNotFoundException {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() throws Exception {
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<List<User>>(userList,HttpStatus.FOUND);
    }
}
