package com.axxes.whoswho.service;

import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.model.Round;

import java.util.List;

public interface RoundService {
    List<Round> getRounds(Person playingPerson);

}
