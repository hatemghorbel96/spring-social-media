package com.example.users.service;

import com.example.users.entity.Chat;
import com.example.users.entity.Message;
import com.example.users.exceptions.ChatAlreadyExistException;
import com.example.users.exceptions.ChatNotFoundException;
import com.example.users.exceptions.NoChatExistsInTheRepository;


import java.util.HashSet;
import java.util.List;

public interface ChatService {

    public Chat addChat(Chat chat) throws ChatAlreadyExistException;

    List<Chat> findallchats() throws NoChatExistsInTheRepository;

    Chat getById(int id)  throws ChatNotFoundException;

    List<Chat> getChatByFirstUserName(String username)  throws ChatNotFoundException;

    List<Chat> getChatBySecondUserName(String username)  throws ChatNotFoundException;

    List<Chat> getChatByFirstUserNameOrSecondUserName(String username)  throws ChatNotFoundException;

    List<Chat> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName)  throws ChatNotFoundException;

    Chat addMessage(Message add, int chatId)  throws ChatNotFoundException;

	Chat getChatByFirstUserNameAndSecondUserNamesingle(String firstUserName, String secondUserName)
			throws ChatNotFoundException;
}
