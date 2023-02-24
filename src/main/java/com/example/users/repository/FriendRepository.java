package com.example.users.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.users.entity.Friend;

@CrossOrigin("http://localhost:4200")
public interface FriendRepository extends  JpaRepository<Friend,Long> {
	
	//boolean existsByFirstUserAndSecondUser(User first,User second);

	/*
	 * List<Friend> findByFirstUser(User user); List<Friend> findBySecondUser(User
	 * user);
	 */
	
	/* @Query("select f from Friend f where f.secondUser = :id and f.accepted = 1" )
		*List<Friend> amis (@Param("id") Integer id); */
	
	 @Query("select f from Friend f where f.user.username like %:username and f.secondUser.id = :id and f.accepted = 0" )
		Friend findByUserUsernameAndSecondUser1 (@Param("username") String username,@Param("id") Long id); 
	 
	 @Query("select f from Friend f where f.user.username like %:username and f.secondUser.id = :id and f.accepted = 1 or f.user.id =:id and f.secondUser.username like %:username and f.accepted = 1" )
		Friend findByUserUsernameAndSecondUser (@Param("username") String username,@Param("id") Long id); 
	 
	 @Query("select f from Friend f where f.secondUser.id = :id and f.accepted = 1 or f.user.id=: id and f.accepted= 1" )
		List<Friend> amis (@Param("id") Long id); 
	 
	 @Query("select f from Friend f where f.user.username like %:username and f.secondUser.id = :id or f.user.id= :id and f.secondUser.username like %:username" )
		Friend getidfriend (@Param("username") String username,@Param("id") Long id);
	 
	 @Query("select f from Friend f where f.user.username like %:username and f.secondUser.id = :id or f.user.id = : id and f.secondUser.username like %:username" )
		Friend getidfriendbyid (@Param("username") String username,@Param("id") Long id);
	 
	 @Query("select f from Friend f where f.secondUser.username like %:username and f.user.id = :id and f.accepted = 0 and f.sender= f.user.username" )
		Friend receivefriendreq (@Param("username") String username,@Param("id") Long id);
	 
	 @Query("select f from Friend f where f.secondUser.username like %:username and f.accepted = 0" )
		List<Friend> notifriendreq (@Param("username") String username);
	 

	
}
