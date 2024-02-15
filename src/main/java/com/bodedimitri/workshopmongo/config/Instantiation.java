package com.bodedimitri.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.bodedimitri.workshopmongo.domain.Post;
import com.bodedimitri.workshopmongo.domain.User;
import com.bodedimitri.workshopmongo.dto.AuthorDTO;
import com.bodedimitri.workshopmongo.dto.CommentDTO;
import com.bodedimitri.workshopmongo.repository.PostRepository;
import com.bodedimitri.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!",sdf.parse("2018/03/21"),new AuthorDTO(alex));
		
		postRepository.saveAll(Arrays.asList(post1));
		
		maria.getPost().addAll(Arrays.asList(post1));
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		post1.getComments().addAll(Arrays.asList(c1));
		postRepository.saveAll(Arrays.asList(post1));
	}

}
