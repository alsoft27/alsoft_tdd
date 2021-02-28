package com.alsoft27.tdd;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.alsoft27.tdd.domain.Person;
import com.alsoft27.tdd.service.PersonService;
import com.github.javafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTestStandalone {

	private MockMvc mvc;

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

		when(this.personService.getPersonById(idNum)).thenReturn(personOpt);

		this.mvc.perform(get("/persons/{id}", idNum)).andExpect(status().isOk()).andExpect(jsonPath("id").value(idNum))
				.andExpect(jsonPath("name").value(name));
	}
}
