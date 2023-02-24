package com.example.users.service;

import com.example.users.entity.Chat;
import com.example.users.entity.Message;
import com.example.users.exceptions.ChatNotFoundException;
import com.example.users.exceptions.NoChatExistsInTheRepository;
import com.example.users.repository.ChatRepository;
import com.example.users.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Transactional
@Service

public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;
   
    
    @Autowired
    private MessageRepository messRep;

    public Chat addChat(Chat chat) {
    	
	//	chat.setChatId(sequenceGeneratorService.generateSequence(chat.getChatId()));
        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> findallchats() throws NoChatExistsInTheRepository {
        if (chatRepository.findAll().isEmpty()) {
            throw new NoChatExistsInTheRepository();
        } else {
            return chatRepository.findAll();
        }

    }

    @Override
    public Chat getById(int id) throws ChatNotFoundException {
        Optional<Chat> chatid = chatRepository.findById(id);
        if (chatid.isPresent()) {
            return chatid.get();
        } else {
            throw new ChatNotFoundException();
        }
    }

    @Override
    public List<Chat> getChatByFirstUserName(String username) throws ChatNotFoundException {
    	List<Chat> chat = chatRepository.getChatByFirstUserName(username);

        if (chat.isEmpty()) {
            throw new ChatNotFoundException();
        } else {
            return chat;
        }
    }

    @Override
    public List<Chat> getChatBySecondUserName(String username) throws ChatNotFoundException {
    	List<Chat> chat = chatRepository.getChatBySecondUserName(username);
        if (chat.isEmpty()) {
            throw new ChatNotFoundException();
        } else {
            return chat;
        }
    }

    @Override
    public List<Chat> getChatByFirstUserNameOrSecondUserName(String username) throws ChatNotFoundException {
    	List<Chat> chat = chatRepository.getChatByFirstUserName(username);
    	List<Chat> chat1 = chatRepository.getChatBySecondUserName(username);

        chat1.addAll(chat);

        if (chat.isEmpty() && chat1.isEmpty()) {
            throw new ChatNotFoundException();
        } else if (chat1.isEmpty()) {
            return chat;
        } else {
            return chat1;
        }
    }

    @Override
    public List<Chat> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName) throws ChatNotFoundException {
    	List<Chat> chat = chatRepository.getChatByFirstUserNameAndSecondUserName(firstUserName, secondUserName);
    	List<Chat> chat1 = chatRepository.getChatBySecondUserNameAndFirstUserName(firstUserName, secondUserName);
        if (chat.isEmpty() && chat1.isEmpty()) {
            throw new ChatNotFoundException();
        } else if (chat.isEmpty()) {
            return chat1;
        } else {
            return chat;
        }
    }
    
    @Override
    public Chat getChatByFirstUserNameAndSecondUserNamesingle(String firstUserName, String secondUserName) throws ChatNotFoundException {
    	Chat chat = chatRepository.getbyidmenu(firstUserName, secondUserName);
    	Chat chat1 = chatRepository.getbyidmenusec(firstUserName, secondUserName);
        if (chat == null && chat1==null) {
            throw new ChatNotFoundException();
        } else if (chat==null) {
            return chat1;
        } else {
            return chat;
        }
    }

    @Override
    public Chat addMessage(Message add, int chatId) throws ChatNotFoundException {
        Optional<Chat> chat=chatRepository.findById(chatId);
        Chat abc=chat.get();
        
        if(abc.getMessageList()==null){
            Set<Message> msg=new HashSet<>();
            
            add.setChat(abc);
           // msg.add(add);
            //abc.setMessageList(msg);
            messRep.save(add);
            return chatRepository.save(abc);
        }else{
        	Set<Message> rates=abc.getMessageList();
        	 add.setChat(abc);
            rates.add(add);
            abc.setMessageList(rates);
            return chatRepository.save(abc);
        }
    }

}
