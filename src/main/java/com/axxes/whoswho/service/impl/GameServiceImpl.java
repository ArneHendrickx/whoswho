package com.axxes.whoswho.service.impl;


import com.axxes.whoswho.model.Game;
import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.repository.GameRepository;
import com.axxes.whoswho.service.GameService;
import com.axxes.whoswho.service.RoundService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final RoundService roundService;
    private final GameRepository gameRepository;

    public GameServiceImpl(RoundService roundService, GameRepository gameRepository) {
        this.roundService = roundService;
        this.gameRepository = gameRepository;
    }


    @Override
    public Game createGameForPerson(Person person) {
        Game game = new Game();
        game.setPerson(person);
        game.setRounds(this.roundService.getRounds(person));

        return game;
    }

    @Override
    public Game saveGame(Game game) {
        game.setScore((int)calculateScore(game));
        gameRepository.save(game);

        return game;
    }

    @Override
    public Optional<Game> getGameById(long id) {
        return gameRepository.findById(id);
    }

    private long calculateScore(Game game) {
        return game.getRounds()
                .stream()
                .filter(round -> round.getGuessedPersonId() == round.getRightPersonId())
                .count();
    }
}