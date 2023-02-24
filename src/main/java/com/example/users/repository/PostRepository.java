package com.example.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.users.entity.Friend;
import com.example.users.entity.Post;
@CrossOrigin("http://localhost:4200")
public interface PostRepository extends JpaRepository<Post,Long> {

	List<Post> findByUserUsername(String username);

	List<Post> findByUserId(Long id);
	
	
	 @Query("select p from Post p where p.user.id = :id " )
	 List<Post> findPostByUserId (@Param("id") Long id); 
	 
	 @Query("select COUNT('l') from Like l where l.post.user.id = :id " )
	 Integer sumlike (@Param("id") Long id);
	 
	 @Query("select l.post from Like l where l.user.username = :username " )
	 List<Post> testlike (@Param("username") String username);
	 
	 @Query("select l.post from Like l where l.user.username != :username " )
	 List<Post> testlikebyother (@Param("username") String username);
	 
	 @Query("select l.post from Like l where l.user.username != :username and l.user.username = username" )
	 List<Post> testest (@Param("username") String username);

	

}
