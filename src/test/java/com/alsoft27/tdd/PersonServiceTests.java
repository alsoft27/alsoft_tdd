package com.alsoft27.tdd;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.alsoft27.tdd.domain.Person;
import com.alsoft27.tdd.exception.PersonNotFoundException;
import com.alsoft27.tdd.repository.PersonRepository;
import com.alsoft27.tdd.service.PersonService;
import com.alsoft27.tdd.service.PersonServiceImpl;

@SpringBootTest
public class PersonServiceTests {

	static class PersonServiceImplTestContextConfiguration {

		@Bean
		public PersonService personService() {
			return new PersonServiceImpl();
		}
	}

	@Autowired
	private PersonService personService;

	@MockBean
	private PersonRepository personRepository;

	private Person person;

//	@BeforeAll
//	public void setUp() {
//		Faker faker = new Faker();
//		String idNum = faker.idNumber().valid();
//		String name = faker.name().firstName();
//		String add = faker.address().fullAddress();
//		String phone = faker.phoneNumber().cellPhone();
//		person = new Person(idNum, name, add, phone);
//		Optional<Person> personOpt = Optional.of(person);
//		Mockito.when(personRepository.findById(idNum)).thenReturn(personOpt);
//	}
//
//	@DisplayName("Consultar una persona por id")
//	public void getPersonById() {
//		Optional<Person> res = personService.getPersonById(person.getId());
//		assertThat(res.get().getName()).isEqualTo(person.getName());
//	}

	@DisplayName("Consultar una persona por id")
	@Test
	public void getPersons() {
		Mockito.when(personRepository.findAll()).thenThrow(new PersonNotFoundException());
		assertThrows(PersonNotFoundException.class, () -> personService.findAll());

	}

}
