package com.axxes.whoswho.service;

import com.axxes.whoswho.model.Game;
import com.axxes.whoswho.model.Person;

public interface GameService {
    Game createGameForPerson(Person person);
    Game saveGame(Game game);

}
