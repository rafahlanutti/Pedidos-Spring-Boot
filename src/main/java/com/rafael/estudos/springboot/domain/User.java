package com.rafael.estudos.springboot.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.rafael.estudos.springboot.dto.UserDTO;

@Document
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String email;
	private String name;

	@DBRef(lazy = true)
	private List<Post> post = new ArrayList<>();

	public User() {
		super();
	}

	public User(String id, String email, String name) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
	}

	public User(UserDTO obj) {
		this.id = obj.getId();
		this.email = obj.getEmail();
		this.name = obj.getName();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
