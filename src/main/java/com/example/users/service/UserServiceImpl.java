package com.example.users.service;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.users.entity.User;
import com.example.users.repository.UserRepository;

import exception.UsernameAlreadyUsedException;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	 @Autowired
		UserRepository userRep;
	 
	 

		@Autowired
		BCryptPasswordEncoder bCryptPasswordEncoder;
		
		@Override
		public User saveUser(User user) {
			
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			return userRep.save(user);
		}
		
		@Override
		public User updateUser(User user) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			return userRep.save(user);
		}
		
		@Override
		public User updatepasswordbyId(User u) {
			u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
			return userRep.save(u);
		}
		
		

		@Override
		public User findUserByUsername(String username) {
			return userRep.findByUsername(username);
		}

		

	

		@Override
		public List<User> findAllUsers() {
			// TODO Auto-generated method stub
			return userRep.findAll() ;
		}


		@Override
		public User getUser(Long id) {
			// TODO Auto-generated method stub
			return  userRep.findById(id).get();
		}
		
		
		

		  

}
