package com.example.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.users.entity.Comment;
import com.example.users.entity.Post;
import com.example.users.entity.User;
import com.example.users.repository.CommentRepository;
import com.example.users.repository.PostRepository;
import com.example.users.repository.UserRepository;



@Transactional
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired  //injection
	PostRepository postrepository;
	
	@Autowired  //injection
	UserRepository userRep;
	
	@Autowired  //injection
	CommentRepository comRep;
	
	
	@Override
	public void addcomment(Long id, String username) {
		User u = userRep.findByUsername(username);
		Post p= postrepository.findById(id).get();
		Comment c = new Comment();
		c.setUser(u);
		c.setPost(p);
		comRep.save(c);
		
		
	}


	@Override
	public Comment saveComment(Comment c) {
		// TODO Auto-generated method stub
		return comRep.save(c);
	}


	@Override
	public Comment updateComment(Comment c) {
		// TODO Auto-generated method stub
		return comRep.save(c);
	}

}
