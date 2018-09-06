package com.axxes.whoswho;

import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.model.Sex;
import com.axxes.whoswho.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"com.axxes.whoswho.controllers","com.axxes.whoswho.repository","com.axxes.whoswho.controllers","com.axxes.whoswho.service"})
public class WhoswhoApplication {

	private List<Person> persons = new ArrayList<>();

	@Autowired
	PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(WhoswhoApplication.class, args);
	}

	@EventListener
	public void seed (ApplicationStartedEvent event) {
		persons.add(new Person("Freddy", "De Vadder", "", Sex.MALE));
		persons.add(new Person("Kevin", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Marloes", "Goesaert", "", Sex.FEMALE));
		persons.add(new Person("Maarten", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Mathijs", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Mats", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Charlien", "Goesaert", "", Sex.FEMALE));
		persons.add(new Person("Stef", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Elize", "Goesaert", "", Sex.FEMALE));
		persons.add(new Person("Benjamin", "Goesaert", "", Sex.FEMALE));
		persons.add(new Person("Jonas", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Jelle", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Bram", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Andrea", "Goesaert", "", Sex.FEMALE));
		persons.add(new Person("Jan", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Tom", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Lieven", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Shauni", "Goesaert", "", Sex.FEMALE));
		persons.add(new Person("Fien", "Goesaert", "", Sex.FEMALE));
		persons.add(new Person("Arne", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Julie", "Goesaert", "", Sex.FEMALE));
		persons.add(new Person("Jorgi", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Thomas", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Greogory", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Shanah", "Goesaert", "", Sex.FEMALE));
		persons.add(new Person("Lode", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Zento", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Kevin", "Goesaert", "", Sex.MALE));
		persons.add(new Person("Sarah", "Goesaert", "", Sex.FEMALE));
		persons.add(new Person("Jana", "Goesaert", "", Sex.FEMALE));
		persons.add(new Person("Kaat", "Goesaert", "", Sex.FEMALE));
		persons.add(new Person("Marieke", "Goesaert", "", Sex.FEMALE));
		persons.add(new Person("Vicky", "Goesaert", "", Sex.FEMALE));

		personRepository.saveAll(persons);
	}
}
