package com.example.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.users.repository.FriendRepository;
import com.example.users.repository.UserRepository;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
    FriendRepository friendRepository;

    @Autowired
    UserRepository userRepository;

	
   
}
