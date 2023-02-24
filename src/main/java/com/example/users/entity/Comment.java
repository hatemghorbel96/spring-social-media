package com.example.users.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString

 public class Comment implements Comparable<Comment>{ 
	/*public class Comment implements Serializable{
	private static final long serialVersionUID = 1L;*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idComment;
	private String content;
	@CreationTimestamp
	private Date dateCreation;
	
	
	
    @UpdateTimestamp
    private Date UpdatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;	
	
	@ManyToOne(fetch = FetchType.LAZY)	
	 @JsonBackReference("post")
	@JoinColumn(name = "postIdPost", referencedColumnName = "idPost")
	
	private Post post;

	@Override
	public int compareTo(Comment o) {
		
		return this.idComment.compareTo(((Comment) o).idComment);
	}
	
	
	
	
}
