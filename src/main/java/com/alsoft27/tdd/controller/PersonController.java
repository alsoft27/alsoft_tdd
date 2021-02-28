package com.alsoft27.tdd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alsoft27.tdd.domain.Person;
import com.alsoft27.tdd.exception.PersonNotFoundException;
import com.alsoft27.tdd.service.PersonService;

@RestController
public class PersonController {

	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@PostMapping("/persons")
	public Person createPerson(@RequestBody Person person) {
		person = this.personService.createPerson(person);
		return person;
	}

	@GetMapping("/persons/{id}")
	public Person getPerson(@PathVariable String id) {
		Optional<Person> person = this.personService.getPersonById(id);
		if (!person.isPresent()) {
			throw new PersonNotFoundException();
		}
		return person.get();
	}

	@GetMapping("/persons")
	public List<Person> listPersons() {
		List<Person> persons = this.personService.findAll();
		return persons;
	}
}
