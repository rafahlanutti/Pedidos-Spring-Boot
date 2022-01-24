package com.rafael.estudos.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.estudos.springboot.domain.Post;
import com.rafael.estudos.springboot.service.PostService;
import com.rafael.estudos.springboot.utils.URL;

@RestController
@RequestMapping(value = "api/v1/posts")
public class PostController {

	@Autowired
	private PostService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {

		var obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}

	@GetMapping(value = "/titlesearch/")
	public ResponseEntity<List<Post>> findByTitleSearch(@RequestParam(value = "text", defaultValue = "") String text) {

		text = URL.decoreParam(text);
		var posts = service.findByTitle(text);
		return ResponseEntity.ok().body(posts);

	}

}
