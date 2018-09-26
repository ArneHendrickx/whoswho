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

    public static final int NUMBER_OF_ROUNDS = 20;
    public static final int NUMBER_OF_PERSONS_IN_ROUND = 4;

    private final PersonRepository personRepository;

    public RoundServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Round> getRounds(Person playingPerson) {
        List<Round> rounds;

        List<Person> allPersons = this.personRepository.findAll();

        //remove playing player from the list of all players
        allPersons.remove(playingPerson);

        Collections.shuffle(allPersons);

        List<Person> selectedPersons = allPersons.subList(0, NUMBER_OF_ROUNDS);

        rounds = selectedPersons
                .stream()
                .map(s -> this.createRound(allPersons, s))
                .collect(Collectors.toList());

        return rounds;
    }

    @Override
    public Round getPracticeRound(Person playingPerson) {
        List<Person> allPersons = this.personRepository.findAll();

        //remove playing player from the list of all players
        allPersons.remove(playingPerson);

        Collections.shuffle(allPersons);

        return createRound(allPersons, allPersons.get(0));
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
        for (int i = 1; i < NUMBER_OF_PERSONS_IN_ROUND; i++){
            Random random = new Random();

            Person person = sameGenderPersons.get(random.nextInt(sameGenderPersons.size()));

            //Don't get a person that is already in the round
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