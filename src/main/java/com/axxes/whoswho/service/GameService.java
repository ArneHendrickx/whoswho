package com.axxes.whoswho.service;

import com.axxes.whoswho.model.Game;
import com.axxes.whoswho.model.Person;
import java.util.Optional;

public interface GameService {
    Game createGameForPerson(Person person);
    Game saveGame(Game game);
    Optional<Game>getGameById(long id);

}
