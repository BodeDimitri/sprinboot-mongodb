package com.bodedimitri.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodedimitri.workshopmongo.domain.Post;
import com.bodedimitri.workshopmongo.repository.PostRepository;
import com.bodedimitri.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository rep;

	
	public Post findById(String id) {
		Optional<Post> user = rep.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text) {
		return rep.findByTitleContainingIgnoreCase(text);
	}
	

}
