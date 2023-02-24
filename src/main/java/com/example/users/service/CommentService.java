package com.example.users.service;

import com.example.users.entity.Comment;

public interface CommentService {
	void addcomment(Long id,String username);
	Comment updateComment(Comment c);
	Comment saveComment(Comment c);
}
