package com.example.users.entity;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Chat {
	
	    public static final String SEQUENCE_NAME = "chat_sequence";

	    @Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer chatId;
	    
	    private String firstUserName;
	    private String secondUserName;
	    
	   
	    
	    @OneToMany(mappedBy="chat", cascade = CascadeType.ALL)
		@JsonIgnoreProperties("chat") 
		private Set<Message> messageList = new HashSet<>();
	    
		
		
		public String getFirstUserName() {
			return firstUserName;
		}
		public void setFirstUserName(String firstUserName) {
			this.firstUserName = firstUserName;
		}
		public String getSecondUserName() {
			return secondUserName;
		}
		public void setSecondUserName(String secondUserName) {
			this.secondUserName = secondUserName;
		}
		
		public static String getSequenceName() {
			return SEQUENCE_NAME;
		}
		public Set<Message> getMessageList() {
			return messageList;
		}
		public void setMessageList(Set<Message> messageList) {
			this.messageList = messageList;
		}
		public Integer getChatId() {
			return chatId;
		}
		public void setChatId(Integer chatId) {
			this.chatId = chatId;
		}

	    
}
