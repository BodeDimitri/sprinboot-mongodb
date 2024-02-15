package com.bodedimitri.workshopmongo.resource;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bodedimitri.workshopmongo.domain.Post;
import com.bodedimitri.workshopmongo.resource.util.URL;
import com.bodedimitri.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts") //endpoint
public class PostResource {
	
	@Autowired
	private PostService service;
	
	
	@GetMapping(value = "/{id}") 
	public ResponseEntity<Post> findById(@PathVariable String id) { 
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/tilesearch") 
	public ResponseEntity<List<Post>> findTitle(@RequestParam(value = "text", defaultValue = "") String text) { //RequestParam e para receber o parametro do metodo pela URL
		text = URL.decodeParam(text);
		List<Post> list= service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/wholeSearch") 
	public ResponseEntity<List<Post>> wholeSearch(@RequestParam String text,@RequestParam String minDate,@RequestParam String maxDate) { 
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = service.wholeSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
	
}
