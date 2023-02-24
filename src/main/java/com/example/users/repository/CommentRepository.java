package com.example.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.users.entity.Comment;


@CrossOrigin("http://localhost:4200")
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	List<Comment> findByPostIdPost(Long id);

}
