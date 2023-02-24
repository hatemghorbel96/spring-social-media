package com.example.users.RestController;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


import javax.servlet.ServletContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import com.example.users.entity.Friend;
import com.example.users.entity.User;
import com.example.users.repository.UserRepository;

import com.example.users.service.UserService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
	
	 @Autowired  ServletContext context;
	 
	 @Autowired
	    private UserRepository userRep;
	 
	 @Autowired
		BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	 @Autowired
	 UserService userservice;
	 
	 public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/img";
	
	 @RequestMapping(method = RequestMethod.POST)
		@ResponseBody
		public String saveSlider(User u,@RequestParam("img") MultipartFile file) {
			
			
			String filename=u.getId() + file.getOriginalFilename();
			Path fileNameAndPath =Paths.get(uploadDirectory,filename);
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
			u.setPhoto(filename);
			userRep.save(u);
			return "Save Data Successfully ! ";
		}
	 
	 @RequestMapping(value="getUser/{id}",method = RequestMethod.GET)
		public User getUserById(@PathVariable("id") Long id) {
		return userservice.getUser(id);
		}
	 
	 @RequestMapping(value="getall",method = RequestMethod.GET)
		public List<User> getAll() {
		return userRep.findAll();
		}
	 
	
	 
	 @RequestMapping(value="getUserByName/{username}",method = RequestMethod.GET)
		public User consulterUserbyusername(@PathVariable("username") String username) {
		return userservice.findUserByUsername(username);
		}
	 
	 @GetMapping(path="/profilphoto/{id}")
		public byte[] getPhoto(@PathVariable("id")Long id) throws Exception{
			User user = userRep.findById(id).get();
			
			return Files.readAllBytes(Paths.get(context.getRealPath("/img/")+user.getPhoto()));
		}
	 
	 @RequestMapping(value = "connected/{username}",method = RequestMethod.PUT)
		public void connected(@PathVariable("username") String username) {
		
		
		 User u = userservice.findUserByUsername(username);
		 u.setConnected(2);
		 u.setDesconnecteAt(null);
		
		 userRep.save(u);	 
		}
	 
	 @RequestMapping(value = "desconnected/{username}",method = RequestMethod.PUT)
		public void desconnected(@PathVariable("username") String username) {
		
		
		 User u = userservice.findUserByUsername(username);
		 u.setConnected(1);
		 Date currentUtilDate = new Date();
			 
		 u.setDesconnecteAt(currentUtilDate);
		 
		
		 userRep.save(u);	 
		}
	 
	 @RequestMapping(value = "updateimage/{id}",method = RequestMethod.PUT)
		@ResponseBody
		public String updateimage(User u,@RequestParam("img") MultipartFile file,@PathVariable("id") Long id) {
			
			User getUser= userRep.findById(id).get();
			
			
				getUser.setFriends(getUser.getFriends());
				getUser.setSecfriends(getUser.getSecfriends());
				getUser.setPosts(getUser.getPosts());
				getUser.setComments(getUser.getComments());
				getUser.setLikes(getUser.getLikes());
				getUser.setRoles(getUser.getRoles());
				String filename=u.getId() + file.getOriginalFilename();
				Path fileNameAndPath =Paths.get(uploadDirectory,filename);
				try {
					Files.write(fileNameAndPath, file.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
				getUser.setPhoto(filename);
				
				userRep.save(getUser);
				
			
			
			return "Save Data Successfully ! ";
		}
	 
	 
	
	 
	
	 
		
	

}
