package com.rafael.estudos.springboot.service;

import java.util.Date;
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
		return repository.seachTitle(title);
	}

	public List<Post> querySarch(String text, Date dataMin, Date dataMax) {

		dataMax = new Date(dataMax.getTime() + 24 * 60 * 60);

		return repository.querySearch(text, dataMin, dataMax);

	}
}
