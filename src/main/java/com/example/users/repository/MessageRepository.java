package com.example.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.users.entity.Message;


public interface MessageRepository extends JpaRepository<Message, Integer> {

}
