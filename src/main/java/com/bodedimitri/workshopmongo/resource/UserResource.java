package com.bodedimitri.workshopmongo.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bodedimitri.workshopmongo.domain.Post;
import com.bodedimitri.workshopmongo.domain.User;
import com.bodedimitri.workshopmongo.dto.UserDTO;
import com.bodedimitri.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users") //endpoint
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping //Get acessar informação
	public ResponseEntity<List<UserDTO>> findAll() { //ResponseEntity retorna status
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList()); //Expressão lambda que passa a List para ListDTO
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}") //users/{id}
	public ResponseEntity<UserDTO> findById(@PathVariable String id) { 
		User obj = service.findById(id);
		UserDTO Dto = new UserDTO(obj);
		return ResponseEntity.ok().body(Dto);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO) { 
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}") //Em deleções use 
	public ResponseEntity<Void> delete(@PathVariable String id) { 
		service.Delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id) { 
		User obj = service.fromDTO(objDTO); //userDTO -> user
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{id}/posts") 
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) { 
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPost());
	}
	
}
