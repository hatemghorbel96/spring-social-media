package com.example.users.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	  private long id;
	
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "user")
	 @JsonBackReference("user")
		/*
		 * @JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,
		 * property = "id")
		 */
	 private User user;
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JsonBackReference("post")
	 @JoinColumn(name = "post")
	 private Post post;
	 
	 private String usernamelike;
	 private Long postlike;
	
		/* private UUID userpostreff = UUID.randomUUID(); */
	
}
