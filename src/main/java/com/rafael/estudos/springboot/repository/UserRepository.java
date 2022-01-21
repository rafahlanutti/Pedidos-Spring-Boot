package com.rafael.estudos.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rafael.estudos.springboot.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
