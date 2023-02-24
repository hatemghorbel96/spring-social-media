package com.example.users.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.users.entity.Comment;
import com.example.users.entity.Post;
import com.example.users.entity.User;
import com.example.users.repository.CommentRepository;
import com.example.users.repository.PostRepository;
import com.example.users.repository.UserRepository;
import com.example.users.service.CommentService;


@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {
	@Autowired
	CommentService commentservice;
	
	@Autowired  //injection
	PostRepository postrepository;
	
	@Autowired  //injection
	UserRepository userRep;
	
	@Autowired  //injection
	CommentRepository comRep;
	
	@RequestMapping(value = "add/{id}/{username}",method = RequestMethod.POST)
	public void addcomment(@PathVariable("id") Long id,@PathVariable("username") String username,@RequestBody Comment c) {
	
	 User u = userRep.findByUsername(username);
	Post p= postrepository.findById(id).get();
	 c.setUser(u);
		c.setPost(p);
		
		comRep.save(c);	 
	}
	
	@RequestMapping(value = "update/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Comment> updatecomment(@PathVariable("id") Long id,@RequestBody Comment c) {
	
	 
		Comment getcomm= comRep.findById(id).get();
		getcomm.setPost(getcomm.getPost());
		getcomm.setUser(getcomm.getUser());
		getcomm.setContent(c.getContent());
		getcomm.setDateCreation(null);
		Comment updatedcom =comRep.save(getcomm);	
		
		return ResponseEntity.ok().body(updatedcom);
	}
	
	@RequestMapping(value="/{idPost}",method = RequestMethod.GET)
	public List<Comment> getCommentByProduitId(@PathVariable("idPost") Long idPost) {
	return comRep.findByPostIdPost(idPost);
	}
	
	@RequestMapping(value="/get/{id}",method = RequestMethod.GET)
	public Comment getCommentById(@PathVariable("id") Long id) {
	return comRep.findById(id).get();
	}
	
	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
	public void deletebyid(@PathVariable("id") Long id) {
			comRep.deleteById(id);
	}
	
	@RequestMapping(path="allcom",method=RequestMethod.GET)
	public List<Comment> getallcomments()
	{
		return comRep.findAll();
	}
}

