package com.example.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.users.entity.Like;
import com.example.users.repository.LikeRepository;

@Transactional
@Service
public class LikeServiceimpl implements LikeService {
	
	
	  @Autowired
	    LikeRepository likerepository;
	  
	@Override
	public List<Like> findAllLikes() {
		// TODO Auto-generated method stub
		return likerepository.findAll();
	}

}
