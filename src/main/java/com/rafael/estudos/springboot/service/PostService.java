package com.rafael.estudos.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.estudos.springboot.domain.Post;
import com.rafael.estudos.springboot.exception.ObjectNotFoundException;
import com.rafael.estudos.springboot.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public Post findById(String id) {

		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post não encontrado"));
	}

	public List<Post> findByTitle(String title) {
		return repository.findByTitleContainingIgnoreCase(title);
	}
}
