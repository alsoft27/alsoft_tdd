package com.alsoft27.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alsoft27.tdd.domain.Person;
import com.alsoft27.tdd.repository.PersonRepository;
import com.github.javafaker.Faker;

@SpringBootTest
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void setUp() {
		Faker faker = new Faker();
		// String idNum = faker.idNumber().valid();
		String name = faker.name().firstName();
		String add = faker.address().fullAddress();
		String phone = faker.phoneNumber().cellPhone();
		Person person = new Person();
		person.setName(name);
		person.setDescription(add);
		person.setTelephone(phone);
		assertThat(personRepository.save(person).getName()).isEqualTo(person.getName());
	}

}
