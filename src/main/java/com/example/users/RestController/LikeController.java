package com.example.users.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.users.entity.Like;
import com.example.users.entity.Post;
import com.example.users.entity.User;
import com.example.users.repository.LikeRepository;
import com.example.users.repository.PostRepository;
import com.example.users.service.LikeService;
import com.example.users.service.PostService;
import com.example.users.service.UserService;

@RestController
@RequestMapping("/like")
@CrossOrigin("*")
public class LikeController {
	
	@Autowired
	PostService postservice;
	
	@Autowired
	PostRepository postrepository;
	
	@Autowired
	UserService userservice;
	
	@Autowired
	LikeService likeservice;
	
	@Autowired
	LikeRepository likerepo;
	
	@RequestMapping(value = "likepost/{username}/{id}",method = RequestMethod.POST)
	@ResponseBody
	public void likepost(@PathVariable("id") long id,@PathVariable("username") String username) {
		
		Like l = new Like();
		User getuser= userservice.findUserByUsername(username);
		Post getPost = postrepository.findById(id).get();
		l.setUser(getuser);
		l.setPost(getPost);
		l.setUsernamelike(username);
		l.setPostlike(id);
		likerepo.save(l);
		
	}

	
	@RequestMapping(value = "dislike/{username}/{id}",method = RequestMethod.DELETE)	
	public void dislike(@PathVariable("id") long id,@PathVariable("username") String username) {
		
		
		
		Like l = likerepo.finduserpost(username,id);
		Long getidlike=l.getId();
		likerepo.deleteById(getidlike);
		
	}
	
	@RequestMapping(value = "ifliked/{username}/{id}",method = RequestMethod.GET)

	public Like ifliked(@PathVariable("id") long id,@PathVariable("username") String username) {
		
		Like l = likerepo.finduserpost(username,id);
		if (l == null) {
			return null;
		}else {
			
			return likerepo.finduserpost(username,id);
		}
		
		
	}
	
	@RequestMapping(value = "mawjoud/{username}",method = RequestMethod.GET)

	public Like mawjoud(@PathVariable("username") String username) {
		
		Like l = likerepo.findByUsernamelike(username);
		if (l == null) {
			return null;
		}else {
			
			return l;
		}
		
		
	}
	
	
	@RequestMapping(path="getlikes",method=RequestMethod.GET)
	public List<Like> getall() {
		
	return likeservice.findAllLikes();
	}
	
	@RequestMapping(path="test/{username}/{postid}",method=RequestMethod.GET)
	public Like test(@PathVariable("username") String username,@PathVariable("postid") Long postid) {
		
	return likerepo.finduserpost(username,postid);
	}
	
	
		
		
	

}
