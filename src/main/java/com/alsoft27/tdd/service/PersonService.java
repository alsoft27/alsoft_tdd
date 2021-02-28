package com.alsoft27.tdd.service;

import java.util.List;
import java.util.Optional;

import com.alsoft27.tdd.domain.Person;
import com.alsoft27.tdd.exception.PersonNotFoundException;

public interface PersonService {

	public Person createPerson(Person person);

	public Optional<Person> getPersonById(String id) throws PersonNotFoundException;

	public List<Person> findAll();

}
