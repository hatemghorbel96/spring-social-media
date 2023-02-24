package com.example.users.entity;




import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPost;
	
	@CreationTimestamp
	private Date createdAt;
	private String content;
	
	 @Column(nullable = true)
	private String photo;

	 
	 @ManyToOne	
	 private User user;
	 
	 @JsonManagedReference("post")
	    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
	 	@OrderBy("dateCreation DESC")
	
	    private List<Comment> comments= new ArrayList<>();
	 	
	 	
	 	
		/*
		 * @JsonIgnore
		 * 
		 * @OneToMany(mappedBy = "post", fetch = FetchType.LAZY,orphanRemoval=true)
		 * private Set<Comment> comments = new HashSet<>();
		 */
	// private List<Comment> comments = new ArrayList<>();
	 
	  
	


	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 @JsonManagedReference("post")
	  private List<Like> likes= new ArrayList<>();
	
	

}
