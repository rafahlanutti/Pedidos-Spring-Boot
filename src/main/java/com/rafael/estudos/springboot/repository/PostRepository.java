package com.rafael.estudos.springboot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rafael.estudos.springboot.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	/**
	 * Referencias
	 * https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
	 * https://docs.spring.io/spring-data/data-document/docs/current/reference/html
	 */
	List<Post> findByTitleContainingIgnoreCase(String text);

	/**
	 * Referências:
	 * https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
	 * https://docs.spring.io/spring-data/data-document/docs/current/reference/html/
	 * https://docs.mongodb.com/manual/reference/operator/query/regex
	 */
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> seachTitle(String text);

	@Query("{$and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2 } }, { $or:  [{ 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' }}]}]}")
	List<Post> querySearch(String text, Date minDate, Date maxDate);
}
