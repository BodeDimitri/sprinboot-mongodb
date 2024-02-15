package com.bodedimitri.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.bodedimitri.workshopmongo.domain.Post;

@Repository //Adiciona os metodos do banco de dados
public interface PostRepository extends MongoRepository<Post, String> { //<Classe, tipo do id>
	
	List<Post> findByTitleContainingIgnoreCase(String text); //Quert MongoDB
	
	@Query("{ title: { $regex: ?0, $options: 'i' } }") //Pesquisa customizada //? -> Usada para usar o parametro do metodo
	List<Post> findByTitle(String text);
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> wholeSearch(String text, Date minDate, Date maxDate);
}


