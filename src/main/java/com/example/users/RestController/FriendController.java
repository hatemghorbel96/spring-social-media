package com.example.users.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.example.users.entity.Friend;
import com.example.users.entity.User;
import com.example.users.repository.FriendRepository;
import com.example.users.service.UserService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FriendController {
	
	@Autowired
    private FriendRepository friendRep;
	
	 @Autowired
	 UserService userservice;
	 
	 
	 
		/*
		 * @RequestMapping(value = "friendreq/{username}/{idfriend}",method =
		 * RequestMethod.POST) public void friendreq(@PathVariable("username") String
		 * username,@PathVariable("idfriend") Long idfriend, Friend f) {
		 * 
		 * User myuser = userservice.findUserByUsername(username); User friend=
		 * userservice.getUser(idfriend); f.setFirstUser(myuser);
		 * f.setSecondUser(friend); f.setAccepted(0);
		 * 
		 * 
		 * friendRep.save(f); }
		 */
	 
	 
		  @RequestMapping(value = "friendreq/{username}/{idfriend}",method =  RequestMethod.POST) 
		  public void friendreq(@PathVariable("username") String username,@PathVariable("idfriend") Long idfriend, Friend f) {
		  
		  User myuser = userservice.findUserByUsername(username); 
		  User secuser = userservice.getUser(idfriend);
		  f.setUser(myuser);
		 // f.setSecondUser(idfriend);
		  f.setSecondUser(secuser);
		  f.setAccepted(0);
		  f.setSender(myuser.getUsername());
		  
		  friendRep.save(f); }
		 
	 
	 @RequestMapping(value = "accept/{idfriend}",method = RequestMethod.PUT)
		public void acceptfriend(@PathVariable("idfriend") Long idfriend) {
		
		
		 Friend f = friendRep.findById(idfriend).get();
		 f.setAccepted(1);
		 
		
		 friendRep.save(f);	 
		}
	 
	 @RequestMapping(value = "findfriendbyreq/{username}/{idfriend}",method = RequestMethod.GET)
		public Friend findfriendbyreq(@PathVariable("username") String username,@PathVariable("idfriend") Long idfriend) {
		
		
		 Friend f = friendRep.getidfriendbyid(username,idfriend);
		Long friendid=f.getId();
		 
		
		return friendRep.findById(friendid).get();
		}
	 
	 
	 @RequestMapping(value = "reject/{username}/{idfriend}",method = RequestMethod.PUT)
		public void Rejectfriend(@PathVariable("username") String username,@PathVariable("idfriend") Long idfriend) {
		
		 Friend f = friendRep.getidfriend(username,idfriend);
		 
		 f.setAccepted(2);
		 
		
		 friendRep.save(f);	 
		}
	 
	 @RequestMapping(value = "delete/{username}/{idfriend}",method = RequestMethod.DELETE)
		public void deletefriend(@PathVariable("username") String username,@PathVariable("idfriend") Long idfriend) {
		
		 Friend f = friendRep.getidfriend(username,idfriend);
		 Long idf=f.getId();
		 
		 
		
		 friendRep.deleteById(idf);	 
		}
	 
	 @RequestMapping(value = "getallfriend",method = RequestMethod.GET)
		public List<Friend> gettall() {	
		 return friendRep.findAll(); 
		}
	 
	 @RequestMapping(value = "getfriendbyid/{id}",method = RequestMethod.GET)
		public Friend gettbyid(@PathVariable("id") Long id) {	
		 return friendRep.findById(id).get(); 
		}
	 
	 @RequestMapping(value = "requested/{username}/{id}",method = RequestMethod.GET)
		public Friend isfrendreq(@PathVariable("username") String username,@PathVariable("id") Long id) {	
		 return friendRep.findByUserUsernameAndSecondUser1(username,id);
		}
	 
	 @RequestMapping(value = "isfriend/{username}/{id}",method = RequestMethod.GET)
		public Friend isfriend(@PathVariable("username") String username,@PathVariable("id") Long id) {	
		 return friendRep.findByUserUsernameAndSecondUser(username,id);
		}
	 
	 @RequestMapping(value = "count/{id}",method = RequestMethod.GET)
		public List<Friend> countamiebyid(@PathVariable("id") Long id) {	
		 return friendRep.amis(id);
		}
	 
	 @RequestMapping(value = "receive/{username}/{id}",method = RequestMethod.GET)
		public Friend receivefriend(@PathVariable("username") String username,@PathVariable("id") Long id) {	
		 return friendRep.receivefriendreq(username,id);
		}
	 
	 
	 @RequestMapping(value = "notireceive/{username}",method = RequestMethod.GET)
		public List<Friend> noticationfriendreq(@PathVariable("username") String username) {	
		 return friendRep.notifriendreq(username);
		}

}
