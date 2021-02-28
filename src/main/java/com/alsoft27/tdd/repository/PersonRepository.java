package com.alsoft27.tdd.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.alsoft27.tdd.domain.Person;

public interface PersonRepository extends MongoRepository<Person, String> {

}
