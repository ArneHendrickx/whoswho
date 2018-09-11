package com.axxes.whoswho.service;

import com.axxes.whoswho.model.Game;
import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.model.Round;

import java.util.Optional;

public interface GameService {
    Game createGameForPerson(Person playingPerson);
    Round createPracticeRound(Person playingPerson);
    Game saveGame(Game game);
    Optional<Game>getGameById(long id);
}
