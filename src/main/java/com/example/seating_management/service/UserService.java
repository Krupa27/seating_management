package com.example.seating_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.seating_management.model.User;
import com.example.seating_management.repository.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;


	public boolean authenticate(String username, String password) {
	    return userRepository.findByUsername(username)
	        .map(user -> user.getPassword().equals(password))
	        .orElse(false);
	}


   

	public User register(User user) {
	    System.out.println("Registering user: " + user.getUsername());
	    return userRepository.save(user);
	}

}
