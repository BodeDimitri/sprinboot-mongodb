package com.bodedimitri.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bodedimitri.workshopmongo.domain.Post;

@Repository //Adiciona os metodos do banco de dados
public interface PostRepository extends MongoRepository<Post, String> { //<Classe, tipo do id>
	
	
}
