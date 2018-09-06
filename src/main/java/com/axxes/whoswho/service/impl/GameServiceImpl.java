package com.axxes.whoswho.service.impl;


import com.axxes.whoswho.model.Game;
import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.service.GameService;
import com.axxes.whoswho.service.RoundService;

public class GameServiceImpl implements GameService {

    private final RoundService roundService;

    public GameServiceImpl(RoundService roundService) {
        this.roundService = roundService;
    }


    @Override
    public Game createGameForPerson(Person person) {
        Game game = new Game();
        game.setPerson(person);
        game.setRounds(this.roundService.getRounds(person));

        return game;
    }
}