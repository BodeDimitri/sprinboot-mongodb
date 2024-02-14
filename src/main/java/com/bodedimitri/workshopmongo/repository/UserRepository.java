package com.bodedimitri.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bodedimitri.workshopmongo.domain.User;

@Repository //Adiciona os metodos do banco de dados
public interface UserRepository extends MongoRepository<User, String> { //<Classe, tipo do id>
	
	
}
