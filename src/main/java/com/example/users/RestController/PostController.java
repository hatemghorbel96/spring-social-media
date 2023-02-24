package com.example.users.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.users.entity.Like;
import com.example.users.entity.Post;
import com.example.users.entity.User;
import com.example.users.repository.LikeRepository;
import com.example.users.repository.PostRepository;
import com.example.users.service.PostService;
import com.example.users.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin (origins = "*")
public class PostController {
	
	@Autowired
	PostService postservice;
	
	@Autowired
	PostRepository postrepository;
	
	@Autowired
	UserService userservice;
	
	@Autowired
	LikeRepository likerepo;
	
	@Autowired  ServletContext context;
	
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/img";
	
	@GetMapping(path="/imagePost/{id}")
	public byte[] getPhoto(@PathVariable("id")Long id) throws Exception{
		Post post = postrepository.findById(id).get();
		
		return Files.readAllBytes(Paths.get(context.getRealPath("/img/")+post.getPhoto()));
	}
	
	
	
	@RequestMapping(value = "postphoto/{username}",method = RequestMethod.POST)
	@ResponseBody
	public String postphoto(Post p,@RequestParam("img") MultipartFile file,@PathVariable("username") String username) {
		
		
		String filename=p.getIdPost() + file.getOriginalFilename();
		Path fileNameAndPath =Paths.get(uploadDirectory,filename);
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.setPhoto(filename);
		
		User getuser= userservice.findUserByUsername(username);
		p.setUser(getuser);
		postrepository.save(p);
		return "Save Data Successfully ! ";
	}
	
	@RequestMapping(value = "poststatut/{username}",method = RequestMethod.POST)
	@ResponseBody
	public String poststatutt(@RequestBody Post p,@PathVariable("username") String username) {
		
		
		User getuser= userservice.findUserByUsername(username);
		p.setUser(getuser);
		postrepository.save(p);
		return "Save Data Successfully ! ";
	}
	
	@RequestMapping(path="myposts/{username}",method=RequestMethod.GET)
	public List<Post> getAllpostbyusername(@PathVariable("username") String username)
	{
		return postrepository.findByUserUsername(username);
	}
	
	@RequestMapping(path="getpostbyid/{id}",method=RequestMethod.GET)
	public Post postbyid(@PathVariable("id") Long id)
	{
		return postrepository.findById(id).get();
	}
	
	/*
	 * @RequestMapping(path="otherposts/{id}",method=RequestMethod.GET) public
	 * List<Post> getAllpostbyid(@PathVariable("id") Long id) { return
	 * postrepository.findByUserId(id); }
	 */
	
	@RequestMapping(path="otherposts/{id}",method=RequestMethod.GET)
	public List<Post> getAllpostbyid(@PathVariable("id") Long id)
	{
		return postrepository.findPostByUserId(id);
	}
	
	@RequestMapping(path="allpost",method=RequestMethod.GET)
	public List<Post> getallposts()
	{
		return postrepository.findAll();
	}
	
	 @RequestMapping(value="getsum/{id}",method = RequestMethod.GET)
		public Integer getsum(@PathVariable("id") Long id) {
		return postrepository.sumlike(id);
		}
	 
	 @RequestMapping(value="testlike/{username}",method = RequestMethod.GET)
		public List<Post> testlikeuser(@PathVariable("username") String username) {
		return postrepository.testlike(username);
		}
	 
	 @RequestMapping(value="testlikebyother/{username}",method = RequestMethod.GET)
		public List<Post> testlikeuserbyother(@PathVariable("username") String username) {
		return postrepository.testlikebyother(username);
		}
	 
	 @RequestMapping(value="testtest/{username}",method = RequestMethod.GET)
		public List<Post> teste(@PathVariable("username") String username) {
		return postrepository.testest(username);
		}

}
