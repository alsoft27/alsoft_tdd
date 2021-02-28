package com.alsoft27.tdd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alsoft27.tdd.domain.Person;
import com.alsoft27.tdd.exception.PersonNotFoundException;
import com.alsoft27.tdd.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;

	@Override
	public Person createPerson(Person person) {
		return personRepository.save(person);
	}

	@Override
	public Optional<Person> getPersonById(String id) throws PersonNotFoundException {
		return personRepository.findById(id);
	}

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

}
