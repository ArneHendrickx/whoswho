package com.axxes.whoswho.service.impl;

import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.model.Round;
import com.axxes.whoswho.model.Sex;
import com.axxes.whoswho.repository.PersonRepository;
import com.axxes.whoswho.service.RoundService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
class RoundServiceImplTest {

    private Person personPlaying;
    private List<Person> persons = new ArrayList<>();

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoundService roundService;

    @BeforeEach
    void setUp() {
        personPlaying = new Person("Benjamin", "Goesaert", "", Sex.MALE);

        persons.add(personPlaying);
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

    @Test
    void PersonPlayingDoesNotExistInRound() {
        List<Round> rounds = roundService.getRounds(personPlaying);


        rounds.forEach(round -> {
            round.getPersons().forEach(person -> {
                if (person.getId() == personPlaying.getId()) {
                    fail("Person playing is present in rounds");
                }
            });
        });
    }
}