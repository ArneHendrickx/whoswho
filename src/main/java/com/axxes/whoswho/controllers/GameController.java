package com.axxes.whoswho.controllers;


import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.model.Sex;
import com.axxes.whoswho.repository.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {

    private final PersonRepository personRepository;

    public GameController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public Iterable<Person> getPersons() {
        personRepository.save(new Person("Freddy", "De Vadder", "", Sex.MALE));
        return personRepository.findAll();
    }
}
