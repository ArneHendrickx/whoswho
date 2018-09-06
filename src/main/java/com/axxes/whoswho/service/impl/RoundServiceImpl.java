package com.axxes.whoswho.service.impl;


import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.model.Round;
import com.axxes.whoswho.repository.PersonRepository;
import com.axxes.whoswho.service.RoundService;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RoundServiceImpl implements RoundService {

    public static final int NUMBEROFROUNDS = 20;
    public static final int NUMBEOFPERSONSINROUND = 4;

    private final PersonRepository personRepository;

    public RoundServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Round> getRounds(Person playingPerson) {
        List<Round> rounds;

        List<Person> allPersonsExceptPlayingPerson = Lists.newArrayList(this.personRepository.findAll());
        Collections.shuffle(allPersonsExceptPlayingPerson);

        List<Person> selectedPersons = allPersonsExceptPlayingPerson.subList(0, NUMBEROFROUNDS);

        rounds = selectedPersons
                .stream()
                .map(s -> this.createRound(allPersonsExceptPlayingPerson, s))
                .collect(Collectors.toList());

        return rounds;
    }

    private Round createRound(List<Person> allPersonsExceptPlayingPerson, Person selectedPerson){
        List<Person> personsInRound = new ArrayList<>();
        personsInRound.add(selectedPerson);

        List<Person> sameGenderPersons = getPersonsWithSameGender(allPersonsExceptPlayingPerson, selectedPerson);

        personsInRound = generatePersonsInRound(personsInRound, sameGenderPersons);

        Collections.shuffle(personsInRound);

        return new Round(selectedPerson.getId(), personsInRound);
    }

    private List<Person> generatePersonsInRound(List<Person> personsInRound, List<Person> sameGenderPersons) {
        for (int i = 1; i < NUMBEOFPERSONSINROUND; i++){
            Random random = new Random();

            Person person = sameGenderPersons.get(random.nextInt(sameGenderPersons.size()));

            while (personsInRound.contains(person)){
                person = sameGenderPersons.get(random.nextInt(sameGenderPersons.size()));
            }

            personsInRound.add(person);
        }

        return personsInRound;
    }

    private List<Person> getPersonsWithSameGender(List<Person> allPersonsExceptPlayingPerson, Person selectedPerson) {
        return allPersonsExceptPlayingPerson
                    .stream()
                    .filter(person -> person.getSex() == selectedPerson.getSex())
                    .collect(Collectors.toList());
    }
}