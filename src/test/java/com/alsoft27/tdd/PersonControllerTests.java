package com.alsoft27.tdd;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.alsoft27.tdd.conf.PropertieClass;
import com.alsoft27.tdd.controller.PersonController;
import com.alsoft27.tdd.domain.Person;
import com.alsoft27.tdd.exception.PersonNotFoundException;
import com.alsoft27.tdd.service.PersonService;
import com.github.javafaker.Faker;

@ContextConfiguration(classes = { PropertieClass.class, Alsoft27TddApplication.class })
@TestPropertySource(locations = "/defaulttest.properties")
@WebMvcTest(PersonController.class)
public class PersonControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	PropertieClass propertieClass;

	@MockBean
	private PersonService personService;

	@Test
	@DisplayName("Consultar una persona por id")
	public void getPersonById() throws Exception {
		Faker faker = new Faker();
		String idNum = faker.idNumber().valid();
		String name = faker.name().firstName();
		String add = faker.address().fullAddress();
		String phone = faker.phoneNumber().cellPhone();
		Person person = new Person(idNum, name, add, phone);
		Optional<Person> personOpt = Optional.of(person);

		System.out.println("-----> " + propertieClass.retrievePropertyOne());

		when(this.personService.getPersonById(idNum)).thenReturn(personOpt);

		this.mockMvc.perform(get("/persons/{id}", idNum)).andExpect(status().isOk())
				.andExpect(jsonPath("id").value(idNum)).andExpect(jsonPath("name").value(name));
	}

	@Test
	@DisplayName("Consultar una persona por id que no existe")
	public void getPersonByIdException() throws Exception {
		Faker faker = new Faker();
		String idNum = faker.idNumber().valid();

		when(this.personService.getPersonById(idNum)).thenThrow(new PersonNotFoundException());

		this.mockMvc.perform(get("/persons/{id}", idNum)).andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof PersonNotFoundException));
	}

}
