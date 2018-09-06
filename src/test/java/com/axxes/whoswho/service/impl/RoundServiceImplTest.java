package com.axxes.whoswho.service.impl;

import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.model.Round;
import com.axxes.whoswho.model.Sex;
import com.axxes.whoswho.repository.PersonRepository;
import com.axxes.whoswho.service.RoundService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RoundServiceImplTest {

    private Person personPlaying;
    private List<Person> persons = new ArrayList<>();

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoundService roundService;

    @Before
    public void setUp() {
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
    public void personPlayingDoesNotExistInRound() {
        List<Round> rounds = roundService.getRounds(personPlaying);


        rounds.forEach(round -> {
            round.getPersons().forEach(person -> {
                if (person.getId() == personPlaying.getId()) {
                    fail("Person playing is present in rounds");
                }
            });
        });
    }

    @Test
    public void onlySameGenderInRound() {
        List<Round> rounds = roundService.getRounds(personPlaying);

        rounds.forEach(round -> {
            List<Person> persons = round.getPersons();
            Enum<Sex> genderFirstPerson = persons.get(0).getSex();
            persons.forEach(person -> {
                if (person.getSex() != genderFirstPerson) {
                    fail("Mixed up genders in round");
                }
            });
       });
    }

    @Test
    public void twentyRoundsInGame() {
        List<Round> rounds = roundService.getRounds(personPlaying);
        assertThat(rounds.size()).isEqualTo(20);
    }

    @Test
    public void personOnlyAppearsOnceInRound() {
        List<Round> rounds = roundService.getRounds(personPlaying);

        rounds.forEach(round -> {
            Set<Person> set = new HashSet<>(round.getPersons());
            if (set.size() < round.getPersons().size()) {
                fail("Person appears more then 1 time in round");
            }
        });
    }
}