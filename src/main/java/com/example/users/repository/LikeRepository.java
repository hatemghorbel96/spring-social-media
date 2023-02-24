package com.example.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.users.entity.Friend;
import com.example.users.entity.Like;
import com.example.users.entity.Post;
@CrossOrigin("http://localhost:4200")
public interface LikeRepository extends JpaRepository<Like,Long> {
	

	
	 
	 @Query("select l from Like l where l.usernamelike = :username and l.postlike = :postid" )
		Like finduserpost (@Param("username") String username,@Param("postid") Long postid);

	Like findByUsernamelike(String username); 

}
