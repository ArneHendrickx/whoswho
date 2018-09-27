package com.axxes.whoswho.service.impl;


import com.axxes.whoswho.model.*;
import com.axxes.whoswho.repository.GameRepository;
import com.axxes.whoswho.service.GameService;
import com.axxes.whoswho.service.RoundService;
import com.axxes.whoswho.service.ScoreService;
import com.axxes.whoswho.utils.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final RoundService roundService;
    private final GameRepository gameRepository;
    private final ScoreService scoreService;

    public GameServiceImpl(RoundService roundService, GameRepository gameRepository, ScoreService scoreService) {
        this.roundService = roundService;
        this.gameRepository = gameRepository;
        this.scoreService = scoreService;
    }

    @Override
    public Game createGameForPerson(Person person) {
        Game game = new Game();
        game.setPerson(person);
        game.setRounds(this.roundService.getRounds(person));

        return game;
    }

    @Override
    public Round createPracticeRound(Person playingPerson) {
        return roundService.getPracticeRound(playingPerson);
    }

    @Override
    public AfterGameResult saveGame(Game game) {
        game.setScore((int)calculateScore(game));
        AfterGameResult afterGameResult = createAfterGameResult(game);


        gameRepository.save(game);

        return afterGameResult;

    }

    @Override
    public Optional<Game> getGameById(long id) {
        return gameRepository.findById(id);
    }

    private int calculateScore(Game game) {
        return (int) game.getRounds()
                .stream()
                .filter(round -> round.getGuessedPersonId() == round.getRightPersonId())
                .count();
    }

    private AfterGameResult createAfterGameResult(Game game) {
        List<Game> gamesInCurrentMonth = gameRepository.findByEndTimeBetween(DateTimeUtils.getFirstDayOfMonth(), DateTimeUtils.getLastDayOfMonth());

        AfterGameResult afterGameResult = new AfterGameResult();
        afterGameResult.setCurrentScore(createCurrentScore(game, gamesInCurrentMonth));

        Score previousBestScore = createPreviousBestScore(game, gamesInCurrentMonth);

        afterGameResult.setPreviousBestScore(previousBestScore);
        afterGameResult.setRank(createRank(previousBestScore, game, gamesInCurrentMonth));

        return afterGameResult;

    }

    private Score createCurrentScore(Game game, List<Game> games) {
        Score score = new Score(game.getPerson().getId(),
                game.getPerson().getGivenName(),
                game.getPerson().getSurname(),
                calculateScore(game),
                Duration.between(game.getStartTime(), game.getEndTime()).toMillis(),
                scoreService.getAmountPlayedPerPlayer(games, game.getPerson())
                );

        return score;
    }

    private Score createPreviousBestScore(Game game, List<Game> games) {
        Score score = new Score();
        Optional<Game> bestGameOptional = games.stream()
                .filter(g -> game.getPerson().equals(game.getPerson()))
                .max(Comparator.comparing(Game::getScore).reversed().thenComparing((g1, g2) -> Duration.between(g1.getStartTime(), g1.getEndTime()).toMillis() < Duration.between(g2.getStartTime(), g2.getEndTime()).toMillis() ? 1 : -1));

        if (!bestGameOptional.isPresent()) {
            return null;
        }

        Game bestGame = bestGameOptional.get();
        score.setPlayTimeInMillis(Duration.between(bestGame.getStartTime(), bestGame.getEndTime()).toMillis());
        score.setAmountPlayed(scoreService.getAmountPlayedPerPlayer(games, game.getPerson()));
        score.setFirstName(game.getPerson().getGivenName());
        score.setLastName(game.getPerson().getSurname());
        score.setPersonId(game.getPerson().getId());
        score.setScore(bestGame.getScore());
        return score;
    }

    private int createRank(Score previousBestScore, Game game, List<Game> games) {
        //ToDo : if time

        return 0;
    }
}