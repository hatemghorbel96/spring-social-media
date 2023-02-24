package com.example.users.repository;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.users.entity.Chat;



public interface ChatRepository extends JpaRepository<Chat, Integer> {

	List<Chat> getChatByFirstUserName(String username);

	List<Chat> getChatBySecondUserName(String username);
	
	@Query("select c from Chat c where c.firstUserName like %:firstUserName and c.secondUserName like %:secondUserName")
	List<Chat> getChatByFirstUserNameAndSecondUserName(@Param("firstUserName") String firstUserName,@Param("secondUserName") String secondUserName);
	@Query("select c from Chat c where c.firstUserName like %:secondUserName and c.secondUserName like %:firstUserName")
	List<Chat> getChatBySecondUserNameAndFirstUserName(@Param("firstUserName") String firstUserName,@Param("secondUserName") String secondUserName);
	
	
	@Query("select c from Chat c where c.firstUserName like %:firstUserName and c.secondUserName like %:secondUserName")
	Chat getbyidmenu(@Param("firstUserName") String firstUserName,@Param("secondUserName") String secondUserName);
	
	
	@Query("select c from Chat c where c.firstUserName like %:secondUserName and c.secondUserName like %:firstUserName")
	Chat getbyidmenusec(@Param("firstUserName") String firstUserName,@Param("secondUserName") String secondUserName);

}
