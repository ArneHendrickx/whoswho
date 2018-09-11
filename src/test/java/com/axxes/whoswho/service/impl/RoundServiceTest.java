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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoundServiceTest {

    private Person personPlaying;
    private List<Person> persons = new ArrayList<>();

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoundService roundService;

    @Before
    public void setUp() {
        personPlaying = new Person("2","Benjamin", "Goesaert", "", Sex.MALE);

        persons.add(personPlaying);
        persons.add(new Person("1","Freddy", "De Vadder", "", Sex.MALE));
        persons.add(new Person("2","2,Kevin", "Goesaert", "", Sex.MALE));
        persons.add(new Person("3","2,Marloes", "Goesaert", "", Sex.FEMALE));
        persons.add(new Person("4","2,Maarten", "Goesaert", "", Sex.MALE));
        persons.add(new Person("5","Charlien", "Goesaert", "", Sex.FEMALE));
        persons.add(new Person("6","Stef", "Goesaert", "", Sex.MALE));
        persons.add(new Person("7","Elize", "Goesaert", "", Sex.FEMALE));
        persons.add(new Person("8","Benjamin", "Goesaert", "", Sex.FEMALE));
        persons.add(new Person("9","Jonas", "Goesaert", "", Sex.MALE));
        persons.add(new Person("10","Jelle", "Goesaert", "", Sex.MALE));
        persons.add(new Person("11","Bram", "Goesaert", "", Sex.MALE));
        persons.add(new Person("12","Andrea", "Goesaert", "", Sex.FEMALE));
        persons.add(new Person("12","Jan", "Goesaert", "", Sex.MALE));
        persons.add(new Person("13","Tom", "Goesaert", "", Sex.MALE));
        persons.add(new Person("14","Lieven", "Goesaert", "", Sex.MALE));
        persons.add(new Person("15","Shauni", "Goesaert", "", Sex.FEMALE));
        persons.add(new Person("16","Fien", "Goesaert", "", Sex.FEMALE));
        persons.add(new Person("17","Arne", "Goesaert", "", Sex.MALE));
        persons.add(new Person("18","Julie", "Goesaert", "", Sex.FEMALE));
        persons.add(new Person("19","Jorgi", "Goesaert", "", Sex.MALE));
        persons.add(new Person("20","Thomas", "Goesaert", "", Sex.MALE));
        persons.add(new Person("21","Greogory", "Goesaert", "", Sex.MALE));
        persons.add(new Person("22","Shanah", "Goesaert", "", Sex.FEMALE));
        persons.add(new Person("23","Lode", "Goesaert", "", Sex.MALE));
        persons.add(new Person("24","Zento", "Goesaert", "", Sex.MALE));
        persons.add(new Person("25","Kevin", "Goesaert", "", Sex.MALE));
        persons.add(new Person("26","Sarah", "Goesaert", "", Sex.FEMALE));
        persons.add(new Person("27","Jana", "Goesaert", "", Sex.FEMALE));
        persons.add(new Person("28","Kaat", "Goesaert", "", Sex.FEMALE));
        persons.add(new Person("29","Marieke", "Goesaert", "", Sex.FEMALE));
        persons.add(new Person("30","Vicky", "Goesaert", "", Sex.FEMALE));

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
            Sex genderFirstPerson = persons.get(0).getSex();
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