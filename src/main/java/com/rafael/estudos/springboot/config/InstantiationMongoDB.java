package com.rafael.estudos.springboot.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rafael.estudos.springboot.domain.Post;
import com.rafael.estudos.springboot.domain.User;
import com.rafael.estudos.springboot.dto.CommentDTO;
import com.rafael.estudos.springboot.repository.PostRepository;
import com.rafael.estudos.springboot.repository.UserRepository;

@Configuration
public class InstantiationMongoDB implements CommandLineRunner {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PostRepository postRepository;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void run(String... args) throws Exception {
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		repository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		Post post1 = new Post(null, sdf.parse("21/03/2020"), "Partiu Viagem", "Vou Viajar", maria);
		Post post2 = new Post(null, sdf.parse("21/03/2020"), "Partiu Viagem", "Vou Viajar", maria);

		CommentDTO commentDTO1 = new CommentDTO("Boa viagem mano", sdf.parse("21/03/2020"), alex);
		CommentDTO commentDTO2 = new CommentDTO("Aproveite", sdf.parse("22/03/2020"), bob);
		CommentDTO commentDTO3 = new CommentDTO("Tenha um ótimo dia", sdf.parse("21/03/2020"), maria);

		post1.getComments().addAll(Arrays.asList(commentDTO1, commentDTO2));
		post2.getComments().add(commentDTO3);
	
		
		repository.saveAll(Arrays.asList(maria, alex, bob));
		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.getPost().addAll(Arrays.asList(post1, post2));

		repository.save(maria);
	}

}
