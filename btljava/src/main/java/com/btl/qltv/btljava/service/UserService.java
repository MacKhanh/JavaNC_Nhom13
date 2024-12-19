package com.btl.qltv.btljava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btl.qltv.btljava.entity.User;
import com.btl.qltv.btljava.respository.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;

	public List<User> getAllUsers() {
	        return userRepository.findAll();
	}
	public User saveUser(User user) {
        return userRepository.save(user);
    }
	public User findEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
}
