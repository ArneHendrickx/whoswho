package com.axxes.whoswho;

import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.model.Sex;
import com.axxes.whoswho.repository.PersonRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"com.axxes.whoswho.controllers","com.axxes.whoswho.repository","com.axxes.whoswho.controllers","com.axxes.whoswho.service", "com.axxes.whoswho.configuration"})
public class WhoswhoApplication {

	private List<Person> persons = new ArrayList<>();

	@Autowired
	PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(WhoswhoApplication.class, args);
	}

	/*@Bean
	CommandLineRunner runner(PersonRepository personRepository) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Person>> typeReference = new TypeReference<List<Person>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/persons.json");
			try {
				List<Person> users = mapper.readValue(inputStream,typeReference);
				personRepository.saveAll(users);
				System.out.println("Users Saved!");
			} catch (IOException e){
				System.out.println("Unable to save users: " + e.getMessage());
			}
		};
	}*/
}
