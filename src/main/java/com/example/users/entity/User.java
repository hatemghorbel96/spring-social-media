package com.example.users.entity;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long id;
	@Column(unique = true)
	  private String username;	  
	  private String email;
	  private String nom;
	  private String password;	 
	  private String photo;
	  private Integer connected = 1;
	  
	  private Date desconnecteAt;
	  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
		private Set<Role> roles;
	  
	  
	  @JsonIgnore
	  @OneToMany(mappedBy = "user", fetch=FetchType.LAZY)

	  private List<Friend> friends = new ArrayList<>();
	  
	 
	  @JsonIgnore
	  @OneToMany(mappedBy = "user", fetch=FetchType.LAZY)

	  private List<Friend> secfriends = new ArrayList<>();
	  
	  
	 
	  @OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
	  @JsonIgnore
	  private List<Post> posts = new ArrayList<>();
	  
	  @OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
	  @JsonIgnore
	  private List<Post> comments = new ArrayList<>();
	  
	  @OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
		/*
		 * @JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,
		 * property = "id")
		 */
	  @JsonManagedReference("user")
	  private List<Like> likes = new ArrayList<>();
	  
	  
}
